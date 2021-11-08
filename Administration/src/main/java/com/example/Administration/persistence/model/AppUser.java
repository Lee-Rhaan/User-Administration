package com.example.Administration.persistence.model;

import com.example.Administration.infrastructure.enums.UserRoles;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    @Column(name = "role")
    private UserRoles userRoles;

    public AppUser(String username, String password, UserRoles userRoles)
    {
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
    }
}
