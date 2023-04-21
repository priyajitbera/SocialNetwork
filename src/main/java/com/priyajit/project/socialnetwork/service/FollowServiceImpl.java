package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.dto.responseDTO.FollowResponseDTO;
import com.priyajit.project.socialnetwork.model.Follow;
import com.priyajit.project.socialnetwork.model.User;
import com.priyajit.project.socialnetwork.model.factory.FollowFactory;
import com.priyajit.project.socialnetwork.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService{

    private final FollowRepository followRepository;

    @Autowired
    public FollowServiceImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public Follow getById(Long id) {
        return followRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Follow> getAllByIds(List<Long> ids) {
        return followRepository.findAllById(ids);
    }

    @Override
    public Follow create(Follow follow) {
        return followRepository.save(follow);
    }

    @Override
    public List<User> getFollowees(User follower) {

        List<Follow> followList = followRepository.findAllByFollower(follower);
        return followList.stream().map(Follow::getFollowee).toList();
    }
}
