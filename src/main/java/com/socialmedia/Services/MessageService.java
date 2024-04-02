package com.socialmedia.Services;

import com.socialmedia.model.Message;
import com.socialmedia.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    public Message createMessage(User user,long chatId,Message message) throws Exception;
    public List<Message> getChatsMessage(long chatId) throws Exception;
}
