package com.priyajit.project.socialnetwork.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends Model{

    @ManyToOne
    @JoinColumn(name = "replier_id", foreignKey = @ForeignKey(name = "FK_REPLY_USER"))
    private User replier;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "FK_REPLY_POST"))
    private Post post;

    @ManyToOne
    @JoinColumn(name = "reply_id", foreignKey = @ForeignKey(name = "FK_REPLY_REPLY"))
    private Reply reply;

    @Column(nullable = false)
    private String replyText;
}
