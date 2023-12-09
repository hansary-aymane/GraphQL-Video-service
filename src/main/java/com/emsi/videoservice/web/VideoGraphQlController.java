package com.emsi.videoservice.web;

import com.emsi.videoservice.dao.entities.Creator;
import com.emsi.videoservice.dao.entities.Video;
import com.emsi.videoservice.dto.CreatorDTO;
import com.emsi.videoservice.dto.VideoDTO;
import com.emsi.videoservice.mappers.CreatorDTOMapper;
import com.emsi.videoservice.mappers.VideoDTOMapper;
import com.emsi.videoservice.repositories.CreatorRepository;
import com.emsi.videoservice.repositories.VideoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VideoGraphQlController {
    private CreatorRepository creatorRepository;
    private VideoRepository videoRepository;
    private CreatorDTOMapper creatorDTOMapper;
    private VideoDTOMapper videoDTOMapper;

    VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository, CreatorDTOMapper creatorDTOMapper, VideoDTOMapper videoDTOMapper){
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
        this.creatorDTOMapper = creatorDTOMapper;
        this.videoDTOMapper = videoDTOMapper;
    }

    @QueryMapping
    public List<VideoDTO> videoList() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream()
                .map(videoDTOMapper::fromVideoToVideoDto)
                .collect(Collectors.toList());
    }

    @QueryMapping
    public CreatorDTO creatorById(@Argument Long id) {
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
        return creatorDTOMapper.fromCreatorToCreatorDto(creator);
    }

    /*@MutationMapping
    public Video saveVideo(@Argument Video video){
        return videoRepository.save(video) ;
    }*/

    @MutationMapping
    public Video saveVideo(@Argument VideoDTO videoDTO) {
        Video video = videoDTOMapper.fromVideoDtoToVideo(videoDTO);
        return videoRepository.save(video);
    }

    @MutationMapping
    public CreatorDTO saveCreator(@Argument CreatorDTO creatorDTO){
        // Convert CreatorDTO to Creator entity using your mapper
        Creator creator = creatorDTOMapper.fromCreatorDtoToCreator(creatorDTO);
        // Save the Creator entity using the repository
        Creator savedCreator = creatorRepository.save(creator);
        // Convert the saved Creator entity back to CreatorDTO and return
        return creatorDTOMapper.fromCreatorToCreatorDto(savedCreator);
    }

    /*@MutationMapping
    public Creator saveCreator(@Argument Creator creator){
        return creatorRepository.save(creator);
    }*/
}
