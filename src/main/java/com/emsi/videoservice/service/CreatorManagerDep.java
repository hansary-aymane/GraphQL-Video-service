package com.emsi.videoservice.service;

import com.emsi.videoservice.dto.CreatorDTO;
import org.springframework.graphql.data.method.annotation.Argument;

public interface CreatorManagerDep {
    public CreatorDTO creatorById(@Argument Long id);
    public CreatorDTO saveCreator(@Argument CreatorDTO creatorDTO);
}
