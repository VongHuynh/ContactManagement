package com.example.ContactsManagement.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Convert {
    @Autowired
    ModelMapper modelMapper;
    public <E, EDTO> EDTO toDto(E entity, Class<EDTO> DTOClass) {
        EDTO DTO = modelMapper.map(entity, DTOClass);
        return DTO;
    }
    public <E, EDTO> E toEntity(EDTO DTO, Class<E> EClass) {
        E entity = modelMapper.map(DTO,EClass);
        return entity;
    }

}
