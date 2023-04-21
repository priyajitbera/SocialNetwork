package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.PostResponseDTO;
import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.Vote;

import java.util.List;

public interface PostResponseDTOFactory {

    PostResponseDTO create(Post post);

    PostResponseDTO create(Post post, Long voteCount, Long postCount);

    PostResponseDTO create(Post post, List<Vote> voteList, List<Reply> replyList);
}
