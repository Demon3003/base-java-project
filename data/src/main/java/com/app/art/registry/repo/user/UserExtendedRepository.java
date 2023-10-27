package com.app.art.registry.repo.user;

import com.app.art.registry.model.user.User;

import java.util.List;

public interface UserExtendedRepository {

    void updateUsersInBatch(List<User> users, int batchSize);
}
