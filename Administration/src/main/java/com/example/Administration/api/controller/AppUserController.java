package com.example.Administration.api.controller;

import com.example.Administration.api.service.AppUserService;
import com.example.Administration.persistence.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the controller class. It specifies the endpoints you'd have to use to get access to
 * this server.
 * The base request: "/api/v1/users/"
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    private final AppUserService appUserService;

    /**
     * How to access this method: "/api/v1/users/all"
     * @return list of all users stored in database
     */
    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> listAllUsers()
    {
        List<AppUser> users = appUserService.listAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * How to access this method: "/api/v1/users/create"
     * create and saves new user in database.
     * @param appUser object
     * @return new saved user
     */
    @PostMapping("/create")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser)
    {
        AppUser newAppUser = appUserService.createUser(appUser);
        return new ResponseEntity<>(newAppUser, HttpStatus.CREATED);
    }

    /**
     * How to access this method: "/api/v1/users/update"
     * If user exists in database, user's information gets updated
     * @param appUser object
     * @return updated user
     */
    @PutMapping("/update")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser appUser)
    {
        AppUser updatedUser = appUserService.updateUser(appUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /**
     * How to access this method: "/api/v1/users/find/{id}"
     * Checks if user with matching id exists in database
     * @param id Long
     * @return user or exception
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<AppUser> findUserById(@PathVariable("id") Long id)
    {
        AppUser appUser = appUserService.findUserById(id);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    /**
     * How to access this method: "/api/v1/users/delete/{id}"
     * If user with matching id exists in database, user gets removed.
     * @param id Long
     * @return Successful http status (200)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id)
    {
        appUserService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
