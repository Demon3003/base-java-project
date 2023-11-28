package com.app.art.registry.repo.post;

import com.app.art.registry.model.post.PostDetails;

import java.math.BigInteger;

public interface PostExtendedRepository {

    PostDetails getPostsDetailsById(BigInteger id);
}
