package com.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String content;
    private String image;
    private LocalDateTime timeStamp;
    @ManyToOne
    private User user;
    @ManyToOne
    @JsonIgnore
    private Chat chat;
}
