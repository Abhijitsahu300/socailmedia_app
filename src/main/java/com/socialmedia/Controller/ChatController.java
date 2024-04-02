package com.socialmedia.Controller;

import com.socialmedia.Services.ChatServices;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.Chat;
import com.socialmedia.model.User;
import com.socialmedia.requst.ChatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatServices chatServices;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestBody ChatRequest req,@RequestHeader ("Authorization")String jwt) throws Exception {
        User reqUser=userService.findUserByJwtToken(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat=chatServices.createChat(reqUser,user2);
       return chat;
    }
    @GetMapping("/api/chats")
    public  List<Chat> findUsersChat(@RequestHeader ("Authorization")String jwt){
        User user=userService.findUserByJwtToken(jwt);
        List<Chat> chats = chatServices.findUsersChat(user.getId());

        return chats;

    }
}
