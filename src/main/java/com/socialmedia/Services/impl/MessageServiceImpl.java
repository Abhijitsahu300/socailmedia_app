package com.socialmedia.Services.impl;

import com.socialmedia.Repository.ChatRepository;
import com.socialmedia.Repository.MessageRepository;
import com.socialmedia.Services.ChatServices;

import com.socialmedia.Services.MessageService;
import com.socialmedia.model.Chat;
import com.socialmedia.model.Message;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatServices chatServices;

    @Override
    public Message createMessage(User user, long chatId, Message req) throws Exception {
        Message massage=new Message();
        Chat chat=chatServices.findById(chatId);

        massage.setChat(req.getChat());
        massage.setContent(req.getContent());
        massage.setUser(req.getUser());

        massage.setTimeStamp(LocalDateTime.now());
        return messageRepository.save(massage);
    }

    @Override
    public List<Message> getChatsMessage(long chatId) throws Exception {
        Chat chat=chatServices.findById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
