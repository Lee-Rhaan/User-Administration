package com.example.Administration.api.controller;

import com.example.Administration.api.service.AppUserService;
import com.example.Administration.infrastructure.enums.UserRoles;
import com.example.Administration.persistence.model.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(AppUserController.class)
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private static ObjectMapper objectMapper;

    @MockBean
    private AppUserService appUserService;

    @Test
    void listAllUsers() throws Exception {
        AppUser firstUser = new AppUser("King", "123", UserRoles.ROLE_USER);
        AppUser secondUser = new AppUser("John", "123", UserRoles.ROLE_ADMIN);

        List<AppUser> appUsers = new ArrayList<>();
        appUsers.add(firstUser);
        appUsers.add(secondUser);

        Mockito.when(appUserService.listAllUsers()).thenReturn(appUsers);

        MvcResult mvcResult = mockMvc.perform(get("http://localhost:8080/api/v1/users/all"))
                .andExpect(status().isOk()).andReturn();

        /*
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(appUsers);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

         */
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void deleteUserById() {
    }
}