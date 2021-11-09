package com.example.Administration.persistence.repo;

import com.example.Administration.persistence.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserById(Long id);

    void deleteAppUserById(Long id);

    AppUser findAppUserByUsername(String username);
}
