package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.FollowResponseDTO;
import com.priyajit.project.socialnetwork.model.Follow;

public interface FollowResponseDTOFactory {

    FollowResponseDTO create(Follow follow);
}
