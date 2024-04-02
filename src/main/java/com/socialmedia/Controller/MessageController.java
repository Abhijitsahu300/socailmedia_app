package com.socialmedia.Controller;


import com.socialmedia.Services.MessageService;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.Message;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/massage/chat/{chatId}")
    public Message createMassage(@RequestHeader("Authorization")String jwt, @PathVariable long chatId, @RequestBody Message req) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Message message = messageService.createMessage(user,chatId,req);
        return message;

    }

    @GetMapping("/api/massage/chat/{chatId}")
    public List<Message> findChatsMassage(@RequestHeader("Authorization")String jwt, @PathVariable long chatId, @RequestBody Message req) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Message> messages = messageService.getChatsMessage(chatId);
        return messages;


    }






}
