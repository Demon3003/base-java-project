package com.app.art.registry.services.post.impl;

import com.app.art.registry.model.post.Post;
import com.app.art.registry.services.post.PostService;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Service
public class PostServiceImpl implements PostService {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Post createPost(Post p) {
        em.persist(p);
        return p;
    }

    @Override
    @Transactional
    public void deletePost(BigInteger id) {

    }
}
