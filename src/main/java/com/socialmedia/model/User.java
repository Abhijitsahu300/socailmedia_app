package com.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost=new ArrayList<>();

    private List<Long> followers = new ArrayList<>();

    private List<Long> following = new ArrayList<>();

    @Column(name = "reset_token")
    private String resetToken;



}
