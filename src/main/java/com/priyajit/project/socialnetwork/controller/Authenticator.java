package com.priyajit.project.socialnetwork.controller;

import com.priyajit.project.socialnetwork.dto.responseDTO.AuthTokenResponseDTO;
import com.priyajit.project.socialnetwork.model.User;
import com.priyajit.project.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Authenticator {

    private final UserService userService;

    @Autowired
    public Authenticator(UserService userService) {
        this.userService = userService;
    }

    public boolean authenticate(AuthTokenResponseDTO authTokenResponseDTO) {
        User user = userService.getById(authTokenResponseDTO.getUserId());
        return user.getAuthToken().getToken().equals(authTokenResponseDTO.getToken());
    }

}
