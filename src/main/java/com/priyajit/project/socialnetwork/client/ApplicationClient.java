package com.priyajit.project.socialnetwork;

import com.priyajit.project.socialnetwork.controller.PostController;
import com.priyajit.project.socialnetwork.controller.UserController;
import com.priyajit.project.socialnetwork.dto.requestDTO.*;
import com.priyajit.project.socialnetwork.dto.responseDTO.*;
import com.priyajit.project.socialnetwork.model.VoteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class ApplicationClient {

    private final String[] AUTH_COMMAND_SYNTAX = {
            "login <userId> <password>",
            "signup <handle> <firstName> <lastName> <password>"
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
            "logout"
    };

    private UserController userController;
    private PostController postController;

    @Autowired
    public ApplicationClient(UserController userController, PostController postController) {
        this.userController = userController;
        this.postController = postController;
    }

    private AuthTokenResponseDTO authTokenResponseDTO;

    private void executeCommandMethod(String command) {
        if (command.startsWith("login")) {
            handleLogin(command);
        } else if (command.startsWith("signup")) {
            handleSignup(command);
        } else if (command.startsWith("post")) {
            handlePost(command);
        } else if (command.startsWith("follow")) {
            handleFollow(command);
        } else if (command.startsWith("replytopost")) {
            handleReplyToPost(command);
        } else if (command.startsWith("replytoreply")) {
            handleReplyToReply(command);
        } else if (command.startsWith("upvotepost")) {
            handleUpvoteToPost(command);
        } else if (command.startsWith("downvotepost")) {
            handleDownvoteToPost(command);
        } else if (command.startsWith("upvotereply")) {
            handleUpvoteToReply(command);
        } else if (command.startsWith("downvotereply")) {
            handleDownvoteToReply(command);
        } else if (command.startsWith("shownewsfeed")) {
            handleShownewsfeed(command);
        } else if (command.startsWith("logout")) {
            handleLogout();
        } else {
            System.out.println("WRONG COMMAND TRY AGAIN");
        }
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("> > > STARTING SOCIAL NETWORK APPLICATION< < <");

        String command = "";
        while (!command.equals("exit")) {
            // if authenticated
            if (authTokenResponseDTO == null) printAuthCommands();
            else printCommands();

            // scan command and execute corresponding method
            command = scanner.nextLine();
            executeCommandMethod(command);
            // reset
            command = "";
        }

        System.out.println("> > > THANKS FOR USING THE APPLICATION, EXITING... < < <");
    }

    private void handleLogout() {
        authTokenResponseDTO = null;
    }

    private void handleShownewsfeed(String command) {
//        format: shownewsfeed
        List<PostResponseDTO> postResponseDTOList = postController.getFeed(authTokenResponseDTO.getUserId());
        System.out.println("FEED FETCHED");
        System.out.println(postResponseDTOList);
    }

    private void handleDownvoteToReply(String command) {

//      format: downvotereply <replyId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        CreateVoteRequestDTO dto = CreateVoteRequestDTO.builder()
                .voterId(authTokenResponseDTO.getUserId())
                .replyId(Long.valueOf(args[1]))
                .voteType(VoteType.DOWN)
                .build();
        VoteResponseDTO voteResponseDTO = postController.createVoteToReply(dto);
        System.out.println("SUCCESSFULLY DOWNVOTED REPLY");
        System.out.println(voteResponseDTO);
    }

    private void handleUpvoteToReply(String command) {

//      format: upvotereply <replyId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        CreateVoteRequestDTO dto = CreateVoteRequestDTO.builder()
                .voterId(authTokenResponseDTO.getUserId())
                .replyId(Long.valueOf(args[1]))
                .voteType(VoteType.UP)
                .build();
        VoteResponseDTO voteResponseDTO = postController.createVoteToReply(dto);
        System.out.println("SUCCESSFULLY UPVOTED REPLY");
        System.out.println(voteResponseDTO);
    }

    private void handleDownvoteToPost(String command) {

//      format: downvotepost <postId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        CreateVoteRequestDTO dto = CreateVoteRequestDTO.builder()
                .voterId(authTokenResponseDTO.getUserId())
                .postId(Long.valueOf(args[1]))
                .voteType(VoteType.DOWN)
                .build();
        VoteResponseDTO voteResponseDTO = postController.createVoteToPost(dto);
        System.out.println("SUCCESSFULLY DOWNVOTED POST");
        System.out.println(voteResponseDTO);
    }

    private void handleUpvoteToPost(String command) {

//      format: upvotepost <postId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        CreateVoteRequestDTO dto = CreateVoteRequestDTO.builder()
                .voterId(authTokenResponseDTO.getUserId())
                .postId(Long.valueOf(args[1]))
                .voteType(VoteType.UP)
                .build();
        VoteResponseDTO voteResponseDTO = postController.createVoteToPost(dto);
        System.out.println("SUCCESSFULLY UPVOTED POST");
        System.out.println(voteResponseDTO);
    }

    private void handleReplyToReply(String command) {

//      format: replytoreply <replyId> <replyText>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCountAtleast(args.length - 1, 3)) return;

        // extract replyText skipping the command and arg[1]:replyId
        String replyText = command.substring(args[0].length() + args[1].length() + 2);

        CreateReplyRequestDTO dto = CreateReplyRequestDTO.builder()
                .replierId(authTokenResponseDTO.getUserId())
                .replyId(Long.valueOf(args[1])) // reply to which this reply is made
                .replyText(replyText)
                .build();
        ReplyResponseDTO replyResponseDTO = postController.createReplyToReply(dto);
        System.out.println("REPLY MADE SUCCESSFULLY");
        System.out.println(replyResponseDTO);
    }

    private void handleReplyToPost(String command) {

//      format: replytopost <postId> <replyText>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCountAtleast(args.length - 1, 3)) return;

        // extract replyText skipping the command and arg[1]:postId
        String replyText = command.substring(args[0].length() + args[1].length() + 2);

        CreateReplyRequestDTO dto = CreateReplyRequestDTO.builder()
                .replierId(authTokenResponseDTO.getUserId())
                .postId(Long.valueOf(args[1])) // post to which reply is made
                .replyText(replyText)
                .build();
        ReplyResponseDTO replyResponseDTO = postController.createReplyToPost(dto);
        System.out.println("REPLY MADE SUCCESSFULLY");
        System.out.println(replyResponseDTO);
    }

    private void handleFollow(String command) {

//      format: follow <followeeId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        CreateFollowRequestDTO dto = CreateFollowRequestDTO.builder()
                .followerId(authTokenResponseDTO.getUserId())
                .followeeId(Long.valueOf(args[1]))
                .build();
        FollowResponseDTO followResponseDTO = userController.followUser(dto);
        System.out.println("SUCCESSFULLY FOLLOWED");
        System.out.println(followResponseDTO);
    }

    private void handlePost(String command) {

//      format: post <captionText>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCountAtleast(args.length - 1, 2)) return;

        String captionText = command.substring("post ".length());
        CreatePostRequestDTO dto = CreatePostRequestDTO.builder()
                .creatorId(authTokenResponseDTO.getUserId())
                .captionText(captionText)
                .build();
        PostResponseDTO postResponseDTO = postController.createPost(dto);
        System.out.println("POST CREATED");
        System.out.println(postResponseDTO);
        
    }

    private void handleSignup(String command) {

//      format: signup <handle> <firstName> <lastName> <password>
        String[] args = command.split(" ");

        System.out.println(Arrays.toString(args));

        // validation
        if (!validateArgumentCount(args.length - 1, 4)) return;

        CreateUserRequestDTO dto = CreateUserRequestDTO.builder()
                .handle(args[1])
                .firstName(args[2])
                .lastName(args[3])
                .password(args[4])
                .build();
        UserResponseDTO userResponseDTO = userController.createUser(dto);
        System.out.println("USER CREATED WITH BELOW DETAILS");
        System.out.println(userResponseDTO);
    }

    private void handleLogin(String command) {

//      format: login <userId> <password>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 2)) return;

        LoginRequestDTO dto = LoginRequestDTO.builder()
                .userId(Long.valueOf(args[1]))
                .password(args[2])
                .build();
        authTokenResponseDTO = userController.login(dto);
        if (authTokenResponseDTO != null) {
            System.out.println("$ $ $ SUCCESSFULLY LOGGED IN");
        } else {
            System.out.println("$ $ $ LOGIN FAILED!, INVALID CREDENTIALS");
        }
    }

    private boolean validateArgumentCount(int actualCount, int requiredCount) {

        if (actualCount != requiredCount) {
            System.out.println("$ $ $ INVALID ARGUMENTS!!, TRY AGAIN");
            return false;
        }
        return true;
    }

    private boolean validateArgumentCountAtleast(int actualCount, int requiredCount) {

        if (actualCount < requiredCount) {
            System.out.println("$ $ $ INVALID ARGUMENTS!!, TRY AGAIN");
            return false;
        }
        return true;
    }

    private void printAuthCommands() {

        System.out.println("$ $ $ ENTER ONE OF THE FOLLOWING COMMANDS AND PRESS ENTER $ $ $");
        int i = 1;
        for (String command : AUTH_COMMAND_SYNTAX) {
            System.out.println((i++) + ". " + command + " ");
        }
    }

    private void printCommands() {

        System.out.println("$ $ $ ENTER ONE OF THE FOLLOWING COMMANDS AND PRESS ENTER $ $ $");
        int i = 1;
        for (String command : COMMANDS_SYNTAX) {
            System.out.println((i++) + ". " + command);
        }
    }
}
