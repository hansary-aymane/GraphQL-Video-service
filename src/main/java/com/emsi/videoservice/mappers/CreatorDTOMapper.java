package com.emsi.videoservice.mappers;

import com.emsi.videoservice.dao.entities.Creator;
import com.emsi.videoservice.dto.CreatorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatorDTOMapper{
    private final ModelMapper modelMapper;

    @Autowired
    public CreatorDTOMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public CreatorDTO fromCreatorToCreatorDto(Creator creator){
        return modelMapper.map(creator, CreatorDTO.class);
    }

    public Creator fromCreatorDtoToCreator(CreatorDTO creatorDTO){
        return modelMapper.map(creatorDTO, Creator.class);
    }
}