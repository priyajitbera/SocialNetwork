package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.dto.requestDTO.CreatePostRequestDTO;
import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.User;
import com.priyajit.project.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostFactoryImpl implements PostFactory{

    private UserService userService;

    @Autowired
    public PostFactoryImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Post create(CreatePostRequestDTO dto) {

        User creator = userService.getById(dto.getCreatorId());
        return Post.builder()
                .creator(creator)
                .captionText(dto.getCaptionText())
                .build();
    }
}
