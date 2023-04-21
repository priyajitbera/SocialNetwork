package com.priyajit.project.socialnetwork.client;

import com.priyajit.project.socialnetwork.dto.responseDTO.AuthTokenResponseDTO;

public interface ControllerAdapter {

    void handleShowNewsFeed(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handleDownvoteToReply(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handleUpvoteToReply(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handleDownvoteToPost(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handleUpvoteToPost(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handleReplyToReply(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handleReplyToPost(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handleFollow(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handlePost(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handleSignup(String command);

    AuthTokenResponseDTO handleLogin(String command);

    void handleShowPost(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handleShowReply(String command, AuthTokenResponseDTO authTokenResponseDTO);

    void handleShowVote(String command, AuthTokenResponseDTO authTokenResponseDTO);
}
