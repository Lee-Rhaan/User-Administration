package com.example.Administration.persistence.repo;

import com.example.Administration.infrastructure.enums.UserRoles;
import com.example.Administration.infrastructure.exception.AppUserNotFoundException;
import com.example.Administration.persistence.model.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @AfterEach
    void tearDown(){
        appUserRepository.deleteAll();
    }

    AppUser appUser = new AppUser("King", "123", UserRoles.ROLE_USER);

    @Test
    void findAppUserByIdTestSuccessful() {
        //Given
        appUserRepository.save(appUser);

        //When
        Optional<AppUser> expected = appUserRepository.findAppUserById(appUser.getId());

        //Then
        assertThat(expected).isEqualTo(Optional.of(appUser));
    }

    @Test
    void findAppUserByIdTestNotSuccessful() {
        String message = "User not found";

        //Given
        Exception expected = assertThrows(AppUserNotFoundException.class,
                () -> {throw new AppUserNotFoundException(message);});

        //Then
        assertEquals(message, expected.getMessage());
    }

    @Test
    void deleteAppUserByIdTest() {
        //Given
        appUserRepository.save(appUser);

        //When
        appUserRepository.deleteAppUserById(appUser.getId());
        Boolean exists = appUserRepository.existsById(appUser.getId());

        //Then
        assertThat(exists).isFalse();
    }

    @Test
    void findAppUserByUsernameTestSuccessful() {
        //Given
        appUserRepository.save(appUser);

        //When
        AppUser expected = appUserRepository.findAppUserByUsername(appUser.getUsername());

        //Then
        assertThat(expected).isEqualTo(appUser);
    }

    @Test
    void findAppUserByUsernameTestNotSuccessful() {
        String message = "User with username not found";

        //Given
        Exception expected = assertThrows(UsernameNotFoundException.class,
                () -> {throw new UsernameNotFoundException(message);});

        //Then
        assertEquals(message, expected.getMessage());
    }
}