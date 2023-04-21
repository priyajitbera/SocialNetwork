package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.dto.responseDTO.FollowResponseDTO;
import com.priyajit.project.socialnetwork.model.Follow;
import com.priyajit.project.socialnetwork.model.factory.FollowFactory;
import com.priyajit.project.socialnetwork.repository.FollowRepository;

import java.util.List;

public class FollowServiceImpl implements FollowService{

    private FollowRepository followRepository;

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
        return null;
    }
}
