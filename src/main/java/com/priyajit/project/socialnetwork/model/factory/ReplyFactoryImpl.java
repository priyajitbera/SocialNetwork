package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.dto.requestDTO.CreateReplyRequestDTO;
import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.User;
import org.springframework.stereotype.Component;

@Component
public class ReplyFactoryImpl implements ReplyFactory {

    @Override
    public Reply create(CreateReplyRequestDTO dto, User replier, Post post) {

        return Reply.builder()
                .replier(replier)
                .post(post)
                .replyText(dto.getReplyText())
                .build();
    }

    @Override
    public Reply create(CreateReplyRequestDTO dto, User replier, Reply reply) {

        return Reply.builder()
                .replier(replier)
                .reply(reply)
                .replyText(dto.getReplyText())
                .build();
    }
}
