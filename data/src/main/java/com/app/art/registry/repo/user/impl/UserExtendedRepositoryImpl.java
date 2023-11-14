package com.app.art.registry.repo.user.impl;

import com.app.art.registry.model.user.User;
import com.app.art.registry.repo.user.UserExtendedRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserExtendedRepositoryImpl implements UserExtendedRepository {

    @PersistenceContext
    EntityManager em;

    public void updateUsersInBatch(List<User> users, int batchSize) {
        em.unwrap(Session.class).setJdbcBatchSize(batchSize);
        for(User u : users) {
            em.merge(u);
        }
    }
}
