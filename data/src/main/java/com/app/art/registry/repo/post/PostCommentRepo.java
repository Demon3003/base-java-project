package com.app.art.registry.repo.post;

import com.app.art.registry.model.post.PostComment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Optional;

public interface PostCommentRepo extends JpaRepository<PostComment, BigInteger> {

    @Override
    @EntityGraph(value = "PostComment.post.postDetails", type = EntityGraph.EntityGraphType.LOAD)
    Optional<PostComment> findById(BigInteger bigInteger);


}
