package com.socialmedia.Services;

import com.socialmedia.model.Chat;
import com.socialmedia.model.User;

import java.util.List;

public interface ChatServices {

    public Chat createChat(User reqUser, User user2);
    public Chat findById(long chatId) throws Exception;
    public List<Chat> findUsersChat(long userId);


}
