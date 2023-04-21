package com.priyajit.project.socialnetwork.client;

import com.priyajit.project.socialnetwork.controller.PostController;
import com.priyajit.project.socialnetwork.controller.UserController;
import com.priyajit.project.socialnetwork.dto.requestDTO.*;
import com.priyajit.project.socialnetwork.dto.responseDTO.*;
import com.priyajit.project.socialnetwork.model.VoteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ControllerAdapterImpl implements ControllerAdapter {

    private final UserController userController;

    private final PostController postController;

    @Autowired
    public ControllerAdapterImpl(UserController userController, PostController postController) {
        this.userController = userController;
        this.postController = postController;
    }

    @Override
    public void handleShowPost(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: showpost <postId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;
        GetPostRequestDTO dto = GetPostRequestDTO.builder()
                .postId(Long.valueOf(args[1]))
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        PostResponseDTO postResponseDTO = postController.getPost(dto);
        System.out.println(postResponseDTO);
    }

    @Override
    public void handleShowReply(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: showreply <replyId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        GetReplyRequestDTO dto = GetReplyRequestDTO.builder()
                .replyId(Long.valueOf(args[1]))
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        ReplyResponseDTO replyResponseDTO = postController.getReply(dto);
        System.out.println(replyResponseDTO);
    }

    @Override
    public void handleShowVote(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: showvote <voteId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        GetVoteRequestDTO dto = GetVoteRequestDTO.builder()
                .voteId(Long.valueOf(args[1]))
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        VoteResponseDTO voteResponseDTO = postController.getVote(dto);
        System.out.println(voteResponseDTO);
    }

    public void handleShowNewsFeed(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: shownewsfeed
        GetFeedRequestDTO dto = GetFeedRequestDTO.builder()
                .userId(authTokenResponseDTO.getUserId())
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        List<PostResponseDTO> postResponseDTOList = postController.getFeed(dto);
        System.out.println("FEED FETCHED");
        System.out.println(postResponseDTOList);
    }

    public void handleDownvoteToReply(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: downvotereply <replyId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        CreateVoteRequestDTO dto = CreateVoteRequestDTO.builder()
                .voterId(authTokenResponseDTO.getUserId())
                .replyId(Long.valueOf(args[1]))
                .voteType(VoteType.DOWN)
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        VoteResponseDTO voteResponseDTO = postController.createVoteToReply(dto);
        System.out.println("SUCCESSFULLY DOWNVOTED REPLY");
        System.out.println(voteResponseDTO);
    }

    public void handleUpvoteToReply(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: upvotereply <replyId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        CreateVoteRequestDTO dto = CreateVoteRequestDTO.builder()
                .voterId(authTokenResponseDTO.getUserId())
                .replyId(Long.valueOf(args[1]))
                .voteType(VoteType.UP)
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        VoteResponseDTO voteResponseDTO = postController.createVoteToReply(dto);
        System.out.println("SUCCESSFULLY UPVOTED REPLY");
        System.out.println(voteResponseDTO);
    }

    public void handleDownvoteToPost(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: downvotepost <postId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        CreateVoteRequestDTO dto = CreateVoteRequestDTO.builder()
                .voterId(authTokenResponseDTO.getUserId())
                .postId(Long.valueOf(args[1]))
                .voteType(VoteType.DOWN)
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        VoteResponseDTO voteResponseDTO = postController.createVoteToPost(dto);
        System.out.println("SUCCESSFULLY DOWNVOTED POST");
        System.out.println(voteResponseDTO);
    }

    public void handleUpvoteToPost(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: upvotepost <postId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        CreateVoteRequestDTO dto = CreateVoteRequestDTO.builder()
                .voterId(authTokenResponseDTO.getUserId())
                .postId(Long.valueOf(args[1]))
                .voteType(VoteType.UP)
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        VoteResponseDTO voteResponseDTO = postController.createVoteToPost(dto);
        System.out.println("SUCCESSFULLY UPVOTED POST");
        System.out.println(voteResponseDTO);
    }

    public void handleReplyToReply(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: replytoreply <replyId> <replyText>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCountAtleast(args.length - 1, 3)) return;

        // extract replyText skipping the command and arg[1]:replyId
        String replyText = command.substring(args[0].length() + args[1].length() + 2);

        CreateReplyRequestDTO dto = CreateReplyRequestDTO.builder()
                .replierId(authTokenResponseDTO.getUserId())
                .replyId(Long.valueOf(args[1])) // reply to which this reply is made
                .replyText(replyText)
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        ReplyResponseDTO replyResponseDTO = postController.createReplyToReply(dto);
        System.out.println("REPLY MADE SUCCESSFULLY");
        System.out.println(replyResponseDTO);
    }

    public void handleReplyToPost(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: replytopost <postId> <replyText>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCountAtleast(args.length - 1, 3)) return;

        // extract replyText skipping the command and arg[1]:postId
        String replyText = command.substring(args[0].length() + args[1].length() + 2);

        CreateReplyRequestDTO dto = CreateReplyRequestDTO.builder()
                .replierId(authTokenResponseDTO.getUserId())
                .postId(Long.valueOf(args[1])) // post to which reply is made
                .replyText(replyText)
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        ReplyResponseDTO replyResponseDTO = postController.createReplyToPost(dto);
        System.out.println("REPLY MADE SUCCESSFULLY");
        System.out.println(replyResponseDTO);
    }

    public void handleFollow(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: follow <followeeId>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 1)) return;

        CreateFollowRequestDTO dto = CreateFollowRequestDTO.builder()
                .followerId(authTokenResponseDTO.getUserId())
                .followeeId(Long.valueOf(args[1]))
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        FollowResponseDTO followResponseDTO = userController.followUser(dto);
        System.out.println("SUCCESSFULLY FOLLOWED");
        System.out.println(followResponseDTO);
    }

    public void handlePost(String command, AuthTokenResponseDTO authTokenResponseDTO) {

        // command: post <captionText>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCountAtleast(args.length - 1, 2)) return;

        String captionText = command.substring("post ".length());
        CreatePostRequestDTO dto = CreatePostRequestDTO.builder()
                .creatorId(authTokenResponseDTO.getUserId())
                .captionText(captionText)
                .authTokenResponseDTO(authTokenResponseDTO)
                .build();
        PostResponseDTO postResponseDTO = postController.createPost(dto);
        System.out.println("POST CREATED");
        System.out.println(postResponseDTO);

    }

    public void handleSignup(String command) {

        // command: signup <handle> <firstName> <lastName> <password>
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

    public AuthTokenResponseDTO handleLogin(String command) {

        // command: login <userId> <password>
        String[] args = command.split(" ");

        // validation
        if (!validateArgumentCount(args.length - 1, 2)) return null;

        LoginRequestDTO dto = LoginRequestDTO.builder()
                .userId(Long.valueOf(args[1]))
                .password(args[2])
                .build();
        AuthTokenResponseDTO authTokenResponseDTO = userController.login(dto);
        if (authTokenResponseDTO != null) {
            System.out.println("$ $ $ SUCCESSFULLY LOGGED IN");
            return authTokenResponseDTO;
        } else {
            System.out.println("$ $ $ LOGIN FAILED!, INVALID CREDENTIALS");
            return null;
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
}
