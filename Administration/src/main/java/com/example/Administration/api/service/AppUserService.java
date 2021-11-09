package com.example.Administration.api.service;

import com.example.Administration.infrastructure.exception.AppUserNotFoundException;
import com.example.Administration.persistence.model.AppUser;
import com.example.Administration.persistence.repo.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;

@RequiredArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByUsername(username);

        if(appUser == null)
        {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        return new org.springframework.security.core.userdetails.User(appUser.getUsername(),
                appUser.getPassword(), getAuthorities(appUser.getUserRoles().getUserRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role)
    {
        return singletonList(new SimpleGrantedAuthority(role));
    }

    public List<AppUser> listAllUsers()
    {
        return appUserRepository.findAll();
    }

    public AppUser createUser(AppUser appUser)
    {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    public AppUser updateUser(AppUser appUser)
    {
        return appUserRepository.save(appUser);
    }

    public AppUser findUserById(Long id)
    {
        return appUserRepository.findAppUserById(id).orElseThrow(() -> new
                AppUserNotFoundException("User with id: " + id + " not found"));
    }

    @Transactional
    public void deleteUserById(Long id)
    {
        appUserRepository.deleteAppUserById(id);
    }

}
