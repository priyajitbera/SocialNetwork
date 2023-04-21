package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.model.Follow;
import com.priyajit.project.socialnetwork.model.User;
import org.springframework.stereotype.Component;

@Component
public class FollowFactoryImpl implements FollowFactory{

    @Override
    public Follow create(User follower, User followee) {

        return Follow.builder()
                .follower(follower)
                .followee(followee)
                .build();
    }
}
