package com.priyajit.project.socialnetwork.controller;

import com.priyajit.project.socialnetwork.dto.requestDTO.CreateFollowRequestDTO;
import com.priyajit.project.socialnetwork.dto.requestDTO.CreateUserRequestDTO;
import com.priyajit.project.socialnetwork.dto.requestDTO.LoginRequestDTO;
import com.priyajit.project.socialnetwork.dto.responseDTO.AuthTokenResponseDTO;
import com.priyajit.project.socialnetwork.dto.responseDTO.FollowResponseDTO;
import com.priyajit.project.socialnetwork.dto.responseDTO.UserResponseDTO;
import com.priyajit.project.socialnetwork.dto.responseDTO.factory.AuthTokenResponseDTOFactory;
import com.priyajit.project.socialnetwork.dto.responseDTO.factory.FollowResponseDTOFactory;
import com.priyajit.project.socialnetwork.dto.responseDTO.factory.UserResponseDTOFactory;
import com.priyajit.project.socialnetwork.model.Follow;
import com.priyajit.project.socialnetwork.model.User;
import com.priyajit.project.socialnetwork.model.factory.FollowFactory;
import com.priyajit.project.socialnetwork.model.factory.UserFactory;
import com.priyajit.project.socialnetwork.service.FollowService;
import com.priyajit.project.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserController {

    private final UserFactory userFactory;
    private final UserService userService;
    private final UserResponseDTOFactory userResponseDTOFactory;
    private final AuthTokenResponseDTOFactory authTokenResponseDTOFactory;
    private final FollowResponseDTOFactory followResponseDTOFactory;
    private final FollowService followService;
    private final FollowFactory followFactory;

    @Autowired
    public UserController(UserFactory userFactory, UserService userService, UserResponseDTOFactory userResponseDTOFactory, AuthTokenResponseDTOFactory authTokenResponseDTOFactory, FollowResponseDTOFactory followResponseDTOFactory, FollowService followService, FollowFactory followFactory) {

        this.userFactory = userFactory;
        this.userService = userService;
        this.userResponseDTOFactory = userResponseDTOFactory;
        this.authTokenResponseDTOFactory = authTokenResponseDTOFactory;
        this.followResponseDTOFactory = followResponseDTOFactory;
        this.followService = followService;
        this.followFactory = followFactory;
    }

    public UserResponseDTO createUser(CreateUserRequestDTO dto) {

        // create model obj from dto and persist
        User user = userFactory.create(dto);
        User savedUser = userService.create(user);

        // map to response dto and return
        return userResponseDTOFactory.create(savedUser);
    }

    public AuthTokenResponseDTO login(LoginRequestDTO dto) {

        User user = userService.login(dto.getUserId(), dto.getHandle(), dto.getPassword());

        // login failed
        if (user == null) return null;

        return authTokenResponseDTOFactory.create(
                user.getAuthToken(),
                user.getId());
    }

    public FollowResponseDTO followUser(CreateFollowRequestDTO dto) {

        User follower = userService.getById(dto.getFollowerId());
        User followee = userService.getById(dto.getFolloweeId());
        Follow follow = followFactory.create(follower, followee);
        Follow savedFollow = followService.create(follow);

        // map to response dto and return
        return followResponseDTOFactory.create(savedFollow);
    }
}
