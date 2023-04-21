package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.PostResponseDTO;
import com.priyajit.project.socialnetwork.dto.responseDTO.ReplyResponseDTO;
import com.priyajit.project.socialnetwork.dto.responseDTO.VoteResponseDTO;
import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.Vote;
import com.priyajit.project.socialnetwork.strategy.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostResponseFactoryImpl implements PostResponseDTOFactory {

    private final ReplyResponseDTOFactory replyResponseDTOFactory;

    private final VoteResponseDTOFactory voteResponseDTOFactory;

    private final DateFormatter dateFormatter;

    @Autowired
    public PostResponseFactoryImpl(
            ReplyResponseDTOFactory replyResponseDTOFactory,
            VoteResponseDTOFactory voteResponseDTOFactory,
            DateFormatter dateFormatter) {

        this.replyResponseDTOFactory = replyResponseDTOFactory;
        this.voteResponseDTOFactory = voteResponseDTOFactory;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public PostResponseDTO create(Post post) {
        String creatorName = post.getCreator().getFirstName()
                + " "
                + post.getCreator().getLastName();
        String creationDate = dateFormatter.format(post.getCreationDate());

        return PostResponseDTO.builder()
                .postId(post.getId())
                .creatorName(creatorName)
                .creatorHandle(post.getCreator().getHandle())
                .creationDate(creationDate)
                .captionText(post.getCaptionText())
                .build();
    }

    @Override
    public PostResponseDTO create(Post post, Long voteCount, Long replyCount) {
        PostResponseDTO postResponseDTO = create(post);
        postResponseDTO.setVoteCount(voteCount);
        postResponseDTO.setReplyCount(replyCount);
        return postResponseDTO;
    }

    @Override
    public PostResponseDTO create(Post post, List<Vote> voteList, List<Reply> replyList) {

//      map votes  to response dto
        List<VoteResponseDTO> voteResponseDTOList = voteList.stream()
                .map(voteResponseDTOFactory::create).toList();

//      map replies to response dto
        List<ReplyResponseDTO> replyResponseDTOList = replyList.stream()
                .map(replyResponseDTOFactory::create).toList();

        PostResponseDTO postResponseDTO = create(post, (long) voteList.size(), (long) replyList.size());
        postResponseDTO.setVoteResponseDTOList(voteResponseDTOList);
        postResponseDTO.setReplyResponseDTOList(replyResponseDTOList);
        return postResponseDTO;
    }
}
