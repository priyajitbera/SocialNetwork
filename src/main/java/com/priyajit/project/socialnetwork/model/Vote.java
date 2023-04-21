package com.priyajit.project.socialnetwork.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        // constraint ensuring one user can make only one vote to a post
        @UniqueConstraint(name = "UQ_VOTE_POST_ID_VOTER_ID", columnNames = {"post_id", "voter_id"}),

        // constraint ensuring one user can make only one vote to a reply
        @UniqueConstraint(name = "UQ_VOTE_REPLY_ID_VOTER_ID", columnNames = {"reply_id", "voter_id"})})
public class Vote extends Model {

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
    @Column(nullable = false)
    private VoteType voteType;
}
