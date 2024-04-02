package com.socialmedia.Services.impl;

import com.socialmedia.Repository.StoryRepository;
import com.socialmedia.Services.StoryService;
import com.socialmedia.Services.UserService;
import com.socialmedia.model.Story;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;



    @Override
    public Story createStory(Story story, User user) {
        Story createdStory=new Story();
        createdStory.setCaption(story.getCaption());
        createdStory.setUser(user);
        createdStory.setImage(story.getImage());
        createdStory.setTimeStamp(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryUsetrId(long userId) throws Exception {
        User user=userService.findUserById(userId);
        return storyRepository.findByUserId(userId);

    }
}
