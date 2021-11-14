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

/**
 * This service class deals with all the business logic.
 */

@RequiredArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Finding user by username. Then returning it as a UserDetails object if found.
     * (authenticating user)
     * @param username String
     * @return User as UserDetails object
     * @throws UsernameNotFoundException
     */
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

    /**
     * Assigning authorization to user role.
     * @param role String
     * @return authorized user role
     */
    private Collection<? extends GrantedAuthority> getAuthorities(String role)
    {
        return singletonList(new SimpleGrantedAuthority(role));
    }

    /**
     * @return list of users stored in database.
     */
    public List<AppUser> listAllUsers()
    {
        return appUserRepository.findAll();
    }

    /**
     * Encrypts user's password upon creation and saving of new user in database.
     * @param appUser object
     * @return saved user
     */
    public AppUser createUser(AppUser appUser)
    {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    /**
     * Updates an existing user in the database.
     * @param appUser
     * @return
     */
    public AppUser updateUser(AppUser appUser)
    {
        return appUserRepository.save(appUser);
    }

    /**
     * Checks if user with matching id exists in database, if true, returns user - if not, throws
     * an exception with detailed message.
     * @param id Long
     * @return user or exception
     */
    public AppUser findUserById(Long id)
    {
        return appUserRepository.findAppUserById(id).orElseThrow(() -> new
                AppUserNotFoundException("User with id: " + id + " not found"));
    }

    /**
     * Find user by id and removes user from database if user exists.
     * @param id Long
     */
    @Transactional
    public void deleteUserById(Long id)
    {
        appUserRepository.deleteAppUserById(id);
    }

}
