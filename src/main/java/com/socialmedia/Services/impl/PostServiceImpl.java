package com.socialmedia.Services.impl;

import com.socialmedia.Repository.PostRepository;
import com.socialmedia.Repository.UserRepository;
import com.socialmedia.Services.PostService;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.Post;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createPost(Post post, long userId) throws Exception {
        User user = userService.findUserById(userId);

        Post newPost=new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(long postId, long userId) throws Exception {
        Post post=findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getUser().getId()!=user.getId()){
            throw new Exception("you cant delete another users post");

        }
        postRepository.delete(post);
        return "post deleted successfully";
    }



    @Override
    public List<Post> findPostByUserId(long id) {
        return postRepository.findPostByUserId(id);


    }

    @Override
    public Post findPostById(long id) throws Exception {
        Optional<Post> opt = postRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("post not found id "+id);

        }
        return opt.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();

    }

    @Override
    public Post savedPost(long postId, long userId) throws Exception {
        Post post=findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }
        else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);

        return post;
    }

    @Override
    public Post likePost(long postId, long userId) throws Exception {
        Post post=findPostById(postId);
        User user = userService.findUserById(userId);
        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else {

            post.getLiked().add(user);
        }

        return postRepository.save(post);

    }
}
