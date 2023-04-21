package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.ReplyResponseDTO;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.Vote;

import java.util.List;

public interface ReplyResponseDTOFactory {

    ReplyResponseDTO create(Reply reply);

    ReplyResponseDTO create(Reply reply, Long voteCount, Long ReplyCount);

    ReplyResponseDTO create(Reply reply, List<Vote> voteList, List<Reply> replyList);
}
