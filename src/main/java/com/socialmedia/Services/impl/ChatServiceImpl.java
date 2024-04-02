package com.socialmedia.Services.impl;

import com.socialmedia.Repository.ChatRepository;
import com.socialmedia.Services.ChatServices;
import com.socialmedia.model.Chat;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ChatServiceImpl implements ChatServices {
    @Autowired
    private ChatRepository chatRepository;


    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExist=chatRepository.findChatByUsersId(user2,reqUser);

        if(isExist!=null){
            return isExist;
        }

        Chat chat=new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);

        chat.setTimeStamp(LocalDateTime.now());

        return chatRepository.save(chat);
    }

    @Override
    public Chat findById(long chatId) throws Exception {
        Optional<Chat> opt = chatRepository.findById(chatId);

        if(opt.isEmpty()){
            throw new Exception("chat not found with id -"+ chatId);
        }
        return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(long userId) {


        return chatRepository.findByUsersId(userId);
    }
}
