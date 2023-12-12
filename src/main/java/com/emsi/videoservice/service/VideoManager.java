package com.emsi.videoservice.service;

import com.emsi.videoservice.dao.entities.Video;
import com.emsi.videoservice.dto.VideoDTO;
import com.emsi.videoservice.mappers.VideoDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VideoManager implements VideoManagerDep {
    @Autowired
    private com.emsi.videoservice.repositories.VideoRepository videoRepository;
    @Autowired
    private VideoDTOMapper videoDTOMapper;

    public List<VideoDTO> videoList() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream()
                .map(videoDTOMapper::fromVideoToVideoDto)
                .collect(Collectors.toList());
    }

    public Video saveVideo(@Argument VideoDTO videoDTO){
        Video video = videoDTOMapper.fromVideoDtoToVideo(videoDTO);
        return videoRepository.save(video);
    }
}