package com.socialmedia.Services;

import com.socialmedia.model.Story;
import com.socialmedia.model.User;

import java.util.List;

public interface StoryService {


    Story createStory(Story story,User user);

    public List<Story> findStoryUsetrId(long userId) throws Exception;
}
