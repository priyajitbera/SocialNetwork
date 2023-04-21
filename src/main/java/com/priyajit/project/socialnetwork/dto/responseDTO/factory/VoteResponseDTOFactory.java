package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.VoteResponseDTO;
import com.priyajit.project.socialnetwork.model.Vote;

public interface VoteResponseDTOFactory {

    VoteResponseDTO create(Vote vote);
}
