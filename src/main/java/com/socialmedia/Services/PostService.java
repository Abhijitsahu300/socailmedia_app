package com.socialmedia.Services;

import com.socialmedia.model.Post;

import java.util.List;

public interface PostService {

    Post createPost(Post post, long userId) throws Exception;
    //wohe user ka post hai na
    String deletePost(long postId,long userId) throws Exception;

    List<Post> findPostByUserId(long id);

    Post findPostById(long id) throws Exception;

    List<Post> findAllPost();

    Post savedPost(long postId,long userId) throws Exception;

    Post likePost(long postId,long userId) throws Exception;
}
