package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.Vote;

import java.util.List;

public interface ReplyService extends CommonService<Reply, Long>{

    List<Reply> getAllByPost(Post post);

    List<Reply> getAllByReply(Reply reply);

    Long getCountByPost(Post post);

    Long getCountByReply(Reply reply);
}
