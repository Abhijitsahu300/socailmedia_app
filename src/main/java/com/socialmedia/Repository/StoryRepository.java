package com.socialmedia.Repository;

import com.socialmedia.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story ,Long> {

    public List<Story> findByUserId(long userId);
}
