package com.example.Administration.api.controller;

import com.example.Administration.api.service.AppUserService;
import com.example.Administration.persistence.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> listAllUsers()
    {
        List<AppUser> users = appUserService.listAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser)
    {
        AppUser newAppUser = appUserService.createUser(appUser);
        return new ResponseEntity<>(newAppUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser appUser)
    {
        AppUser updatedUser = appUserService.updateUser(appUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AppUser> findUserById(@PathVariable("id") Long id)
    {
        AppUser appUser = appUserService.findUserById(id);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id)
    {
        appUserService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
