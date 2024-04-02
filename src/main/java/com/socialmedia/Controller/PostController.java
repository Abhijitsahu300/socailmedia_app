package com.socialmedia.Controller;

import com.socialmedia.Response.ApiResponse;
import com.socialmedia.Services.PostService;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.Post;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @PostMapping("/api/post")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization")String jwt,@RequestBody Post post) throws Exception {
        User reqUser=userService.findUserByJwtToken(jwt);

        Post createdpost = postService.createPost(post, reqUser.getId());

        return new ResponseEntity<>(createdpost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable long postId,@RequestHeader("Authorization")String jwt) throws Exception {
        User reqUser=userService.findUserByJwtToken(jwt);
        String message=postService.deletePost(postId,reqUser.getId());
         ApiResponse res=new ApiResponse(message,true);
         return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }
    @GetMapping("/api/post/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable long postId) throws Exception {
        Post post=postService.findPostById(postId);

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable long userId){
        List<Post> post = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
    }
    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> post = postService.findAllPost();
        return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
    }
    @PutMapping("/api/post/{postId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable long postId,@RequestHeader("Authorization")String jwt) throws Exception {
        User reqUser=userService.findUserByJwtToken(jwt);
        Post post=postService.savedPost(postId,reqUser.getId());

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/post/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable long postId,@RequestHeader("Authorization")String jwt) throws Exception {
        User reqUser=userService.findUserByJwtToken(jwt);
        Post post=postService.likePost(postId,reqUser.getId());

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }


}
