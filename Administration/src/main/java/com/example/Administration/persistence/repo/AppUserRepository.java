package com.example.Administration.persistence.repo;

import com.example.Administration.persistence.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * I'm extending this interface with "JpaRepository", to get access to all it's CRUD
 * functionalities.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * These are custom abstract methods I am going to be implementing in my service class.
     * The names of these methods are going to be read like queries by Spring.
     */

    //setting it to optional, because this method may or may not return a value.
    Optional<AppUser> findAppUserById(Long id);

    void deleteAppUserById(Long id);

    AppUser findAppUserByUsername(String username);
}
