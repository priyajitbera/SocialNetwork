package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public Reply getById(Long id) {
        return replyRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Reply> getAllByIds(List<Long> ids) {
        return replyRepository.findAllById(ids);
    }

    @Override
    public Reply create(Reply reply) {
        return replyRepository.save(reply);
    }

    @Override
    public List<Reply> getAllByPost(Post post) {
        return replyRepository.findAllByPost(post);
    }

    @Override
    public List<Reply> getAllByReply(Reply reply) {
        return replyRepository.findAllByReply(reply);
    }

    @Override
    public Long getCountByPost(Post post) {
        return replyRepository.countByPost(post);
    }

    @Override
    public Long getCountByReply(Reply reply) {
        return replyRepository.countByReply(reply);
    }
}
