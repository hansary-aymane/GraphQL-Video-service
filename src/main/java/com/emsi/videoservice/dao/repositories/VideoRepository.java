package com.emsi.videoservice.repositories;

import com.emsi.videoservice.dao.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
