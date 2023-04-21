package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.VoteResponseDTO;
import com.priyajit.project.socialnetwork.model.Vote;
import com.priyajit.project.socialnetwork.strategy.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteResponseDTOFactoryImpl implements VoteResponseDTOFactory {

    private final DateFormatter dateFormatter;

    @Autowired
    public VoteResponseDTOFactoryImpl(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public VoteResponseDTO create(Vote vote) {

        String voterName = vote.getVoter().getFirstName()
                + " "
                + vote.getVoter().getLastName();

        String creationDate = dateFormatter.format(vote.getCreationDate());

        return VoteResponseDTO.builder()
                .voteId(vote.getId())
                .voterName(voterName)
                .voterHandle(vote.getVoter().getHandle())
                .creationDate(creationDate)
                .voteType(vote.getVoteType().name())
                .build();
    }
}
