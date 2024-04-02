package com.socialmedia.Services;

import com.socialmedia.model.Reels;
import com.socialmedia.model.User;

import java.util.List;

public interface ReelService {

    public Reels createReels(Reels reels, long userId) throws Exception;
    public List<Reels> findAllReels();
    public List<Reels> findUserReels(long userId) throws Exception;
}
