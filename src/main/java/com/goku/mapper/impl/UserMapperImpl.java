package com.goku.mapper.impl;

import com.goku.api.model.RoleDTO;
import com.goku.api.model.UserDTO;
import com.goku.entity.RoleEntity;
import com.goku.entity.UserEntity;
import com.goku.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity dtoToEntity(UserDTO dto, UserEntity entity) {
        if (dto == null || entity == null) {
            return null;
        }
        entity.setUsername(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

        RoleEntity roleEntity = new RoleEntity();
        dtoToEntity(dto.getRoles(), roleEntity);
        entity.setRoles(roleEntity);

        return entity;
    }

    @Override
    public UserDTO entityToDto(UserEntity entity, UserDTO dto){
        if (dto == null || entity == null) {
            return null;
        }
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());

        RoleDTO roleDto = new RoleDTO();
        entityToDto(entity.getRoles(), roleDto);
        dto.setRoles(roleDto);

        return dto;
    }

    @Override
    public RoleEntity dtoToEntity(RoleDTO dto, RoleEntity entity) {
        if (dto == null || entity == null) {
            return null;
        }
        entity.setId(dto.getId());
        entity.setRoleName(dto.getRoleName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    @Override
    public RoleDTO entityToDto(RoleEntity entity, RoleDTO dto) {
        if (dto == null || entity == null) {
            return null;
        }
        dto.setId(entity.getId());
        dto.setRoleName(entity.getRoleName());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}