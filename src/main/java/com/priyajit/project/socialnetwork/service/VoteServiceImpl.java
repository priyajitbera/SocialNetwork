package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.Vote;
import com.priyajit.project.socialnetwork.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService{

    private final VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote getById(Long id) {
        return voteRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Vote> getAllByIds(List<Long> ids) {
        return voteRepository.findAllById(ids);
    }

    @Override
    public Vote create(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public List<Vote> getAllByPost(Post post) {
        return voteRepository.findAllByPost(post);
    }

    @Override
    public List<Vote> getAllByReply(Reply reply) {
        return voteRepository.findAllByReply(reply);
    }

    @Override
    public Long getCountByPost(Post post) {
        return voteRepository.countByPost(post);
    }

    @Override
    public Long getCountByReply(Reply reply) {
        return voteRepository.countByReply(reply);
    }
}
