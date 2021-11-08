package com.example.Administration.api.service;

import com.example.Administration.infrastructure.exception.AppUserNotFoundException;
import com.example.Administration.persistence.model.AppUser;
import com.example.Administration.persistence.repo.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

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
