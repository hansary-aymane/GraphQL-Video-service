package com.emsi.videoservice.mappers;

import com.emsi.videoservice.dao.entities.Creator;
import com.emsi.videoservice.dao.entities.Video;
import com.emsi.videoservice.dto.CreatorDTO;
import com.emsi.videoservice.dto.VideoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class VideoDTOMapper{
    private final ModelMapper modelMapper;

    @Autowired
    public VideoDTOMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public VideoDTO fromVideoToVideoDto(Video video){
        return modelMapper.map(video, VideoDTO.class);
    }

    public Video fromVideoDtoToVideo(VideoDTO videoDTO){
        return modelMapper.map(videoDTO, Video.class);
    }
}