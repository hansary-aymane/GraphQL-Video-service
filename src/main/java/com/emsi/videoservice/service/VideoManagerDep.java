package com.emsi.videoservice.service;

import com.emsi.videoservice.dao.entities.Video;
import com.emsi.videoservice.dto.VideoDTO;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;

public interface VideoManagerDep {
    public List<VideoDTO> videoList();
    public Video saveVideo(@Argument VideoDTO videoDTO);
}
