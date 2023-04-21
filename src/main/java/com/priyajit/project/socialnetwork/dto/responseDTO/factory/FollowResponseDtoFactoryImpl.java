package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.FollowResponseDTO;
import com.priyajit.project.socialnetwork.model.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FollowResponseDtoFactoryImpl implements FollowResponseDTOFactory {

    private final UserResponseDTOFactory userResponseDTOFactory;

    @Autowired
    public FollowResponseDtoFactoryImpl(UserResponseDTOFactory userResponseDTOFactory) {
        this.userResponseDTOFactory = userResponseDTOFactory;
    }

    @Override
    public FollowResponseDTO create(Follow follow) {

        return FollowResponseDTO.builder()
                .follower(userResponseDTOFactory.create(follow.getFollower()))
                .followee(userResponseDTOFactory.create(follow.getFollowee()))
                .build();
    }
}
