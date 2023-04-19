package com.priyajit.project.socialnetwork.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends Model{

    @ManyToOne
    @JoinColumn(name = "voter_id", foreignKey = @ForeignKey(name = "FK_VOTE_USER"))
    private User voter;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "FK_VOTE_POST"))
    private Post post;

    @ManyToOne
    @JoinColumn(name = "reply_id", foreignKey = @ForeignKey(name = "FK_VOTE_REPLY"))
    private Reply reply;

    @Enumerated(EnumType.STRING)
    private VoteType voteType;
}
