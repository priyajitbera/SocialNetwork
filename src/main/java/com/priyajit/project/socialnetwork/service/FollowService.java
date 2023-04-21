package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.model.Follow;
import com.priyajit.project.socialnetwork.model.User;

import java.util.List;

public interface FollowService extends CommonService<Follow, Long>{

    List<User> getFollowees(User follower);

}
