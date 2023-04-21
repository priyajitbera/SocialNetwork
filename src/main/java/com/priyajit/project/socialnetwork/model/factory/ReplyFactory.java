package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.dto.requestDTO.CreateReplyRequestDTO;
import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.User;

public interface ReplyFactory {

    Reply create(CreateReplyRequestDTO dto, User replier, Post post);

    Reply create(CreateReplyRequestDTO dto, User replier, Reply reply);
}
