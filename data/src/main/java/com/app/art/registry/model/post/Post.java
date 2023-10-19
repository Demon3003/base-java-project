package com.app.art.registry.model.post;

import com.app.art.registry.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Set;

@Table(name = "post")
@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "post_seq", sequenceName = "post_sequence", schema = "public", allocationSize = 20)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    private BigInteger id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Fetch(value = FetchMode.JOIN)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "post")
    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    private PostDetails postDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private Set<PostComment> postComments;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
