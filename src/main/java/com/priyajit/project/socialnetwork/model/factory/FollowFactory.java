package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.model.Follow;
import com.priyajit.project.socialnetwork.model.User;

public interface FollowFactory {

    Follow create(User follower, User followee);
}
