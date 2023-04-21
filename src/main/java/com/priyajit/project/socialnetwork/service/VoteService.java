package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.Vote;

import java.util.List;

public interface VoteService extends CommonService<Vote, Long>{

    List<Vote> getAllByPost(Post post);

    List<Vote> getAllByReply(Reply reply);

    Long getCountByPost(Post post);

    Long getCountByReply(Reply reply);
}
