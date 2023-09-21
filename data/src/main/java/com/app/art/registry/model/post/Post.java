package com.app.art.registry.model.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.math.BigInteger;

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

    private String title;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "post")
    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    private PostDetails postDetails;

}
