package com.app.art.registry.repo;

import com.app.art.registry.model.post.Post;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PostRepo extends JpaRepository<Post, BigInteger> {
}
