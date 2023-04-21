package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.ReplyResponseDTO;
import com.priyajit.project.socialnetwork.dto.responseDTO.VoteResponseDTO;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.Vote;
import com.priyajit.project.socialnetwork.strategy.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReplyResponseDTOFactoryImpl implements ReplyResponseDTOFactory {

    private final VoteResponseDTOFactory voteResponseDTOFactory;

    private final DateFormatter dateFormatter;

    @Autowired
    public ReplyResponseDTOFactoryImpl(VoteResponseDTOFactory voteResponseDTOFactory, DateFormatter dateFormatter) {
        this.voteResponseDTOFactory = voteResponseDTOFactory;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public ReplyResponseDTO create(Reply reply) {

        String replierName = reply.getReplier().getFirstName()
                + " "
                + reply.getReplier().getLastName();

        String creationDate = dateFormatter.format(reply.getCreationDate());

        return ReplyResponseDTO.builder()
                .replyId(reply.getId())
                .replierName(replierName)
                .replierHandle(reply.getReplier().getHandle())
                .creationDate(creationDate)
                .replyText(reply.getReplyText())
                .build();
    }

    @Override
    public ReplyResponseDTO create(Reply reply, Long voteCount, Long replyCount) {

        ReplyResponseDTO replyResponseDTO = create(reply);
        replyResponseDTO.setVoteCount(voteCount);
        replyResponseDTO.setReplyCount(replyCount);
        return replyResponseDTO;
    }

    @Override
    public ReplyResponseDTO create(Reply reply, List<Vote> voteList, List<Reply> replyList) {

        // map votes of this reply to response dto
        List<VoteResponseDTO> voteResponseDTOList = voteList.stream().map(voteResponseDTOFactory::create).toList();

        // map replies to this reply to response dto
        List<ReplyResponseDTO> replyResponseDTOList = replyList.stream().map(this::create).toList();

        ReplyResponseDTO replyResponseDTO = create(reply, (long) voteList.size(), (long) replyList.size());
        replyResponseDTO.setVotes(voteResponseDTOList);
        replyResponseDTO.setReplies(replyResponseDTOList);
        return replyResponseDTO;
    }
}
