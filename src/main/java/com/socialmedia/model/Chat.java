package com.socialmedia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String chat_name;
    private String chat_img;
    private LocalDateTime timeStamp;
    @ManyToMany
    private List<User> users=new ArrayList<>();
    @OneToMany(mappedBy = "chat")  // create in chate not create new table
    private List<Message> massages=new ArrayList<>();


}
