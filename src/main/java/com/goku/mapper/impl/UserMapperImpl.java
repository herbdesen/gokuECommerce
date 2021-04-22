package com.goku.mapper.impl;

import com.goku.api.model.UserDTO;
import com.goku.domain.User;
import com.goku.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User dtoToEntity(UserDTO dto, User entity) {
        if (dto == null || entity == null) {
            return null;
        }
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
    }

    @Override
    public UserDTO entityToDto(User entity, UserDTO dto){
        if (dto == null || entity == null) {
            return null;
        }
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        return dto;
    }
}