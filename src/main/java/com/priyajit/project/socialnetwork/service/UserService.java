package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.model.User;

public interface UserService extends CommonService<User, Long>{

    User getByUserIdOrHandle(Long userId, String handle);

    User login(Long userId, String handle, String secret);
}
