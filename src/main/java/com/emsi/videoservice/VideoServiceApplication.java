package com.emsi.videoservice;

import com.emsi.videoservice.dao.entities.Creator;
import com.emsi.videoservice.dao.entities.Video;
import com.emsi.videoservice.repositories.CreatorRepository;
import com.emsi.videoservice.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class VideoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
		return args -> {
			String[] names = {"aymane", "hansary", "mohammed"};
			String[] emails = {"aymane@gmail.com", "hansary@gmail.com", "mohammed@gmail.com"};

			List<Creator> creators = new ArrayList<>();
			IntStream.range(0, names.length)
					.forEach(i -> {
						Creator creator = new Creator();
						creator.setName(names[i]);
						creator.setEmail(emails[i]);
						creators.add(creator);
					});

			creatorRepository.saveAll(creators);

			Date currentDate = new Date();

			List<Video> videos = new ArrayList<>();
			IntStream.range(0, names.length)
					.forEach(i -> {
						Video video = new Video();
						video.setName("v" + (i + 1));
						video.setUrl("v" + (i + 1) + ".com");
						video.setDatePublication(currentDate);
						video.setDescription("this is video " + (i + 1));
						video.setCreator(creators.get(i));
						videos.add(video);
					});

			videoRepository.saveAll(videos);
		};
	}
}
