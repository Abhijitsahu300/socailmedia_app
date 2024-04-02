package com.socialmedia.Controller;

import com.socialmedia.Services.StoryService;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.Story;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story CreateStory(@RequestBody Story story,@RequestHeader("Authorization")String jwt){
        User reqUser = userService.findUserByJwtToken(jwt);
        Story createdStory=storyService.createStory(story,reqUser);
        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUserStory(@PathVariable long userId, @RequestHeader("Authorization")String jwt) throws Exception {
        User reqUser = userService.findUserByJwtToken(jwt);
        List<Story> createdStory=storyService.findStoryUsetrId(userId);
        return createdStory;
    }
}
