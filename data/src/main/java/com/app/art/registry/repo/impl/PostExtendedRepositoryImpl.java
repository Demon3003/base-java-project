package com.app.art.registry.repo.impl;

import com.app.art.registry.model.post.PostDetails;
import com.app.art.registry.repo.post.PostExtendedRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Component
public class PostExtendedRepositoryImpl implements PostExtendedRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public PostDetails getPostsDetailsById(BigInteger id) {
        PostDetails pd = em.createQuery("from PostDetails pd where pd.id = ?1", PostDetails.class)
                .setParameter(1, id)
                .getSingleResult();
        return pd;
    }
}
