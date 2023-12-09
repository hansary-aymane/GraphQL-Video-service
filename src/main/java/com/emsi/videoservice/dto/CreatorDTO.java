package com.emsi.videoservice.dto;

import com.emsi.videoservice.dao.entities.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatorDTO {
    private Long id;
    private String name;
    private  String email;
    private List<Video> videos;
}
