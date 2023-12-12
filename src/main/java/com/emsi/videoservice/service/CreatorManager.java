package com.emsi.videoservice.service;

import com.emsi.videoservice.dao.entities.Creator;
import com.emsi.videoservice.dto.CreatorDTO;
import com.emsi.videoservice.mappers.CreatorDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Component;

@Component
public class CreatorManager implements CreatorManagerDep{
    @Autowired
    private com.emsi.videoservice.repositories.CreatorRepository creatorRepository;
    @Autowired
    private CreatorDTOMapper creatorDTOMapper;

    @Override
    public CreatorDTO creatorById(Long id) {
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
        return creatorDTOMapper.fromCreatorToCreatorDto(creator);
    }

    @Override
    public CreatorDTO saveCreator(@Argument CreatorDTO creatorDTO){
        Creator creator = creatorDTOMapper.fromCreatorDtoToCreator(creatorDTO);
        Creator savedCreator = creatorRepository.save(creator);
        return creatorDTOMapper.fromCreatorToCreatorDto(savedCreator);
    }
}
