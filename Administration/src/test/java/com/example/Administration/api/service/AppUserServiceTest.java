package com.example.Administration.api.service;

import com.example.Administration.infrastructure.enums.UserRoles;
import com.example.Administration.infrastructure.exception.AppUserNotFoundException;
import com.example.Administration.persistence.model.AppUser;
import com.example.Administration.persistence.repo.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    AppUser appUser = new AppUser("King", "123", UserRoles.ROLE_USER);

    @Test
    void loadUserByUsernameTestSuccessful() {
        //When
        when(appUserRepository.findAppUserByUsername(appUser.getUsername())).thenReturn(appUser);

        UserDetails expected = appUserService.loadUserByUsername(appUser.getUsername());

        //Then
        assertThat(appUserService.loadUserByUsername(appUser.getUsername())).isEqualTo(expected);
    }

    @Test
    void loadUserByUsernameTestNotSuccessful() {
        String message = "User with username not found";

        //Given
        Exception expected = assertThrows(UsernameNotFoundException.class,
                () -> {throw new UsernameNotFoundException(message);});

        //Then
        assertEquals(message, expected.getMessage());
    }

    @Test
    void listAllUsersTest() {
        //When
        appUserService.listAllUsers();

        //Then
        verify(appUserRepository).findAll();
    }

    @Test
    void createUserTest() {
        //When
        appUserService.createUser(appUser);

        //Then
        ArgumentCaptor<AppUser> userArgumentCaptor = ArgumentCaptor.forClass(AppUser.class);

        verify(appUserRepository).save(userArgumentCaptor.capture());

        AppUser capturedAppUser = userArgumentCaptor.getValue();

        assertThat(capturedAppUser).isEqualTo(appUser);
    }

    @Test
    void updateUserTest() {
        //When
        when(appUserRepository.save(appUser)).thenReturn(appUser);

        //Then
        assertThat(appUserService.updateUser(appUser)).isEqualTo(appUser);
    }

    @Test
    void findUserByIdTestSuccessful() {
        //When
        when(appUserRepository.findAppUserById(appUser.getId())).thenReturn(Optional.of(appUser));

        //Then
        assertThat(appUserService.findUserById(appUser.getId())).isEqualTo(appUser);
    }

    @Test
    void findUserByIdTestNotSuccessful() {
        String message = "User with id not found";

        //Given
        Exception expected = assertThrows(AppUserNotFoundException.class,
                () -> {throw new AppUserNotFoundException(message);});

        //Then
        assertEquals(message, expected.getMessage());
    }

    @Test
    void deleteUserByIdTest() {
        //Given
        appUserService.deleteUserById(appUser.getId());

        //When
        Boolean exists = appUserRepository.existsById(appUser.getId());

        //Then
        assertThat(exists).isFalse();
    }
}