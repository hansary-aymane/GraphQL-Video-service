package com.emsi.videoservice.dto;

import com.emsi.videoservice.dao.entities.Creator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {
    private Long id;
    private String name;
    private String url;
    private String description;
    private Date datePublication;
    private Creator creator;
}
