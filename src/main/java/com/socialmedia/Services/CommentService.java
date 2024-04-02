package com.socialmedia.Services;

import com.socialmedia.model.Comment;

public interface CommentService {

    public Comment createComment(Comment comment,long postId,long userId) throws Exception;
    public Comment likeComment(long commentId,long userId) throws Exception;
    public Comment findCommentById(long commentId) throws Exception;
}
