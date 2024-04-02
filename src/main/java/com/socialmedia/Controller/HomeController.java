package com.socialmedia.Controller;

import com.socialmedia.Repository.UserRepository;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;




    @GetMapping("/api/users")
    public List<User> getUser(){
       List<User> users=userRepository.findAll();
        return users;

    }

    @GetMapping("/api/users/{userid}")
    public User findUserById(@PathVariable("userid") long id) throws Exception {
        User user = userService.findUserById(id);
         return user;
    }

    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization")String jwt, @RequestBody User user) throws Exception{

        User reqUser=userService.findUserByJwtToken(jwt);

        return userService.updateUser(user, reqUser.getId());

    }


    @PutMapping("/api/users/follow/{userId2}")
  public User followUserHandeler(@RequestHeader("Authorization")String jwt, @PathVariable long userId2)throws  Exception{
        User reqUser=userService.findUserByJwtToken(jwt);
        User user = userService.followUser(reqUser.getId(), userId2);
         return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query")String query){
        List<User> users=userService.searchUser(query);
        return users;
    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization")String jwt){
        User user=userService.findUserByJwtToken(jwt);

        user.setPassword(null);

        return user;
    }

}
