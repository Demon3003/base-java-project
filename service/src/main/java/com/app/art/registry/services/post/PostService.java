package com.app.art.registry.services.post;

import com.app.art.registry.model.post.Post;

import java.math.BigInteger;

public interface PostService {

    Post createPost(Post p);

    void deletePost(BigInteger id);

}
