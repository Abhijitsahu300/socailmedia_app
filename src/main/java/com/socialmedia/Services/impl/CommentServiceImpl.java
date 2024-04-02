package com.socialmedia.Services.impl;
import com.socialmedia.Repository.CommentRepository;
import com.socialmedia.Repository.PostRepository;
import com.socialmedia.Services.CommentService;
import com.socialmedia.Services.PostService;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.Comment;
import com.socialmedia.model.Post;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    PostService postService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;


    @Override
    public Comment createComment(Comment comment, long postId, long userId) throws Exception {
        User user=userService.findUserById(userId);

        Post post=postService.findPostById(postId);

        comment.setUser(user);
        comment.setComment(comment.getComment());
        comment.setCreateAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        postRepository.save(post);
        return savedComment;

    }

    @Override
    public Comment likeComment(long commentId, long userId) throws Exception {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);
        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else {
            comment.getLiked().remove(user);
        }

        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(long commentId) throws Exception {
        Optional<Comment> opt = commentRepository.findById(commentId);

        if(opt.isEmpty()){
            throw new Exception("comment not exist");
        }



        return opt.get();
    }
}
