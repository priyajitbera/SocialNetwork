package com.priyajit.project.socialnetwork.client;

import com.priyajit.project.socialnetwork.dto.responseDTO.AuthTokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ApplicationClient {

    private final String[] AUTH_COMMAND_SYNTAX = {
            "login <userId> <password>",
            "signup <handle> <firstName> <lastName> <password>",
            "exit"
    };

    private final String[] COMMANDS_SYNTAX = {
            "post <captionText>",
            "follow <followeeId>",
            "replytopost <postId> <replyText>",
            "replytoreply <replyId> <replyText>",
            "upvotepost <postId>",
            "upvotereply <postId>",
            "downvotepost <postId>",
            "downvotereply <replyId>",
            "shownewsfeed",
            "showpost <postId>",
            "showreply <replyId",
            "showvote <voteId>",
            "logout",
            "exit"
    };

    private final ControllerAdapter controllerAdapter;

    @Autowired
    public ApplicationClient(ControllerAdapter controllerAdapter) {
        this.controllerAdapter = controllerAdapter;
    }

    private AuthTokenResponseDTO authTokenResponseDTO;

    private void executeCommand(String command) {
        if (command.startsWith("login")) {
            authTokenResponseDTO = controllerAdapter.handleLogin(command);
        } else if (command.startsWith("signup")) {
            controllerAdapter.handleSignup(command);
        } else if (command.startsWith("post")) {
            controllerAdapter.handlePost(command, authTokenResponseDTO);
        } else if (command.startsWith("follow")) {
            controllerAdapter.handleFollow(command, authTokenResponseDTO);
        } else if (command.startsWith("replytopost")) {
            controllerAdapter.handleReplyToPost(command, authTokenResponseDTO);
        } else if (command.startsWith("replytoreply")) {
            controllerAdapter.handleReplyToReply(command, authTokenResponseDTO);
        } else if (command.startsWith("upvotepost")) {
            controllerAdapter.handleUpvoteToPost(command, authTokenResponseDTO);
        } else if (command.startsWith("downvotepost")) {
            controllerAdapter.handleDownvoteToPost(command, authTokenResponseDTO);
        } else if (command.startsWith("upvotereply")) {
            controllerAdapter.handleUpvoteToReply(command, authTokenResponseDTO);
        } else if (command.startsWith("downvotereply")) {
            controllerAdapter.handleDownvoteToReply(command, authTokenResponseDTO);
        } else if (command.startsWith("shownewsfeed")) {
            controllerAdapter.handleShowNewsFeed(command, authTokenResponseDTO);
        } else if (command.startsWith("showpost")) {
            controllerAdapter.handleShowPost(command, authTokenResponseDTO);
        } else if (command.startsWith("showreply")) {
            controllerAdapter.handleShowReply(command, authTokenResponseDTO);
        } else if (command.startsWith("showvote")) {
            controllerAdapter.handleShowVote(command, authTokenResponseDTO);
        } else if (command.startsWith("logout")) {
            handleLogout();
        } else if (command.equals("exit")) {
            return;
        } else {
            System.out.println("WRONG COMMAND TRY AGAIN");
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("> > > STARTING SOCIAL NETWORK APPLICATION< < <");

        String command = "";
        while (!command.equals("exit")) {
            // reset
            command = "";

            // if authenticated
            if (authTokenResponseDTO == null) printAuthCommands();
            else printCommands();

            // scan command and execute corresponding method
            command = scanner.nextLine();
            executeCommand(command);
        }

        System.out.println("> > > THANKS FOR USING THE APPLICATION, EXITING... < < <");
    }

    private void handleLogout() {
        authTokenResponseDTO = null;
    }

    private void printAuthCommands() {

        System.out.println("$ $ $ ENTER ONE OF THE FOLLOWING COMMANDS WITH ARGUMENTS AND PRESS ENTER $ $ $");
        int i = 1;
        for (String command : AUTH_COMMAND_SYNTAX) {
            System.out.println((i++) + ". " + command + " ");
        }
    }

    private void printCommands() {

        System.out.println("$ $ $ ENTER ONE OF THE FOLLOWING COMMANDS WITH ARGUMENTS AND PRESS ENTER $ $ $");
        int i = 1;
        for (String command : COMMANDS_SYNTAX) {
            System.out.println((i++) + ". " + command);
        }
    }
}
