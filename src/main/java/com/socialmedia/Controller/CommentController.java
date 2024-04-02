package com.socialmedia.Controller;

import com.socialmedia.Services.CommentService;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.Comment;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestHeader Comment comment, @RequestHeader("Authorization")String jwt, @PathVariable long postId) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Comment createdComment=commentService.createComment(comment,postId,user.getId());
        return createdComment;

    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment( @RequestHeader("Authorization")String jwt, @PathVariable long commentId) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Comment likedComment=commentService.likeComment(commentId, user.getId());
        return likedComment;

    }


}
