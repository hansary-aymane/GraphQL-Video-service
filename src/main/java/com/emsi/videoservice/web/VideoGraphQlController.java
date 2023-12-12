package com.emsi.videoservice.web;

import com.emsi.videoservice.dao.entities.Creator;
import com.emsi.videoservice.dao.entities.Video;
import com.emsi.videoservice.dto.CreatorDTO;
import com.emsi.videoservice.dto.VideoDTO;
import com.emsi.videoservice.mappers.CreatorDTOMapper;
import com.emsi.videoservice.mappers.VideoDTOMapper;
import com.emsi.videoservice.repositories.CreatorRepository;
import com.emsi.videoservice.repositories.VideoRepository;
import com.emsi.videoservice.service.CreatorManager;
import com.emsi.videoservice.service.CreatorManagerDep;
import com.emsi.videoservice.service.VideoManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller @AllArgsConstructor @NoArgsConstructor
public class VideoGraphQlController {
    private CreatorRepository creatorRepository;
    private VideoRepository videoRepository;
    private CreatorDTOMapper creatorDTOMapper;
    private VideoDTOMapper videoDTOMapper;
    private CreatorManagerDep creatorManagerDep;
    private CreatorManager creatorManager;
    private VideoManager videoManager;

    /*VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository, CreatorDTOMapper creatorDTOMapper, VideoDTOMapper videoDTOMapper, CreatorManagerDep creatorManagerDep){
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
        this.creatorDTOMapper = creatorDTOMapper;
        this.videoDTOMapper = videoDTOMapper;
        this.creatorManagerDep = creatorManagerDep;
    }*/

    @QueryMapping
    public List<VideoDTO> videoList() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream()
                .map(videoDTOMapper::fromVideoToVideoDto)
                .collect(Collectors.toList());
    }

    @QueryMapping
    public CreatorDTO creatorById(@Argument Long id) {
        return creatorManagerDep.creatorById(id);
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoDTO videoDTO) {
        Video video = videoDTOMapper.fromVideoDtoToVideo(videoDTO);
        return videoRepository.save(video);
    }

    @MutationMapping
    public CreatorDTO saveCreator(@Argument CreatorDTO creatorDTO){
        return creatorManagerDep.saveCreator(creatorDTO);
    }

/*    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Random random = new Random();
                    CreatorRequest creatorRequest = CreatorRequest.builder().name("x" +
                                    new Random().nextInt())
                            .email("x@gmail.com").build();
                    Creator creator = creatorManager.saveCreator(creatorRequest);
                    Video video = videoManager.findById(1L);
                    video.setCreator(creator);
                    videoManager.updateVideo(video);
                    return video;
                }));
    }*/

}


