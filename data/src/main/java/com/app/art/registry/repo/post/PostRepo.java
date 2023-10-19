package com.app.art.registry.repo.post;

import com.app.art.registry.model.post.Post;
import com.app.art.registry.model.post.PostComment;
import com.app.art.registry.projection.post.PostLight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, BigInteger> {

    PostLight findPostByTitle(String title);

    @Query("select p from Post p where p.id in (select pc.id from PostComment pc where pc.id = ?1)")
    Post findPostByCommentId(BigInteger id);

    @Query("select p from Post p left join PostDetails pd on pd.id = p.id where pd.createdOn < function('current_date') - 2")
    List<Post> getPostIdAndCurrentDate();

    @Query("select p from Post p join fetch p.postDetails where p.postComments is empty and p.postDetails.createdOn > current_date() - 7")
    List<Post> getLatestPostsWithoutComments();

    @Query("select p from Post p where size(p.postComments) > ?1")
    List<Post> getPostsWithCommentsAmount(int commentsNumber);

    @Query("select p from Post p where ?1 member of p.postComments")
    List<Post> getPostsWithComment(PostComment comment);

    @Query(nativeQuery = true, value = "select text from post where id = ?1")
    String getPostTextById(BigInteger id);

    @Query(nativeQuery = true, value = "select id as id, text as text, title as title from post where id = ?1")
    PostLight getPostLight(BigInteger id);


}
