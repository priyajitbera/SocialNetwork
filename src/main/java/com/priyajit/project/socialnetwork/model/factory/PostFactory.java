package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.dto.requestDTO.CreatePostRequestDTO;
import com.priyajit.project.socialnetwork.model.Post;

public interface PostFactory {

    Post create(CreatePostRequestDTO dto);
}
