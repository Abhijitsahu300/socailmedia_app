package com.socialmedia.Services.impl;

import com.socialmedia.Repository.ReelsRepository;
import com.socialmedia.Services.ReelService;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.Reels;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReelsServiceImpl implements ReelService {
    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;
    @Override
    public Reels createReels(Reels reels, long userId) throws Exception {
        User user = userService.findUserById(userId);
        Reels createReels=new Reels();
          createReels.setTitle(reels.getTitle());
          createReels.setUser(user);
          createReels.setVideo(reels.getVideo());

        return reelsRepository.save(createReels);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();

    }

    @Override
    public List<Reels> findUserReels(long userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);

    }
}
