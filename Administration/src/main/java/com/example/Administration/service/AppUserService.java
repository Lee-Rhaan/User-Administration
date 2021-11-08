package com.example.Administration.service;

import com.example.Administration.exception.AppUserNotFoundException;
import com.example.Administration.model.AppUser;
import com.example.Administration.repo.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public List<AppUser> listAllUsers()
    {
        return appUserRepository.findAll();
    }

    public AppUser createUser(AppUser appUser)
    {
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

    public void deleteUserById(Long id)
    {
        appUserRepository.deleteAppUserById(id);
    }
}
