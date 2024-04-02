package com.socialmedia.Repository;

import com.socialmedia.model.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels,Long> {
    public List<Reels> findByUserId(long uerId);
}
