package com.example.Administration;

import com.example.Administration.api.service.AppUserService;
import com.example.Administration.persistence.model.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.Administration.infrastructure.enums.UserRoles.ROLE_ADMIN;
import static com.example.Administration.infrastructure.enums.UserRoles.ROLE_USER;

@SpringBootApplication
public class AdministrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministrationApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(AppUserService appUserService)
	{
		return args -> {
			appUserService.createUser(new AppUser("john", "123", ROLE_ADMIN));
			appUserService.createUser(new AppUser("ben", "123", ROLE_USER));
		};
	}
}
