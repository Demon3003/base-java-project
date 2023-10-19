package com.app.art.registry.services.post.impl;

import com.app.art.registry.model.post.Post;
import com.app.art.registry.services.post.PostService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Service
public class PostServiceImpl implements PostService {

    @PersistenceContext
    EntityManager em;


    @Override
    public Post createPost(Post p) {

        return null;
    }

    @Override
    public void deletePost(BigInteger id) {

    }
}
