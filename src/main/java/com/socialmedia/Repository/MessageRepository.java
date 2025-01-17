package com.socialmedia.Repository;

import com.socialmedia.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    public List<Message> findByChatId(long chatId);
}
