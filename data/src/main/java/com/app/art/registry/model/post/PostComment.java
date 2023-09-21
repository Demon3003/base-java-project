package com.app.art.registry.model.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "post_comment")
@NoArgsConstructor
@SequenceGenerator(name = "post_comment_seq", sequenceName = "post_comment_sequence", allocationSize = 50)
public class PostComment {

    @Id
    @GeneratedValue(generator = "post_comment_seq")
    private BigInteger id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
