package com.socialmedia.Controller;

import com.socialmedia.Services.ReelService;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.Reels;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {
    @Autowired
    private ReelService reelService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization")String jwt) throws Exception {

        User reqUser = userService.findUserByJwtToken(jwt);
        Reels createdreels=reelService.createReels(reels,reqUser.getId());
        System.out.println(reqUser);
         return createdreels;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels(){

        return reelService.findAllReels();

    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findReelsUserId(@PathVariable long userId) throws Exception {

        return reelService.findUserReels(userId);

    }







}
