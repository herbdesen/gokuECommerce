package com.goku.mapper.impl;

import com.goku.api.model.RoleDTO;
import com.goku.api.model.UserDTO;
import com.goku.domain.Role;
import com.goku.domain.User;
import com.goku.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User dtoToEntity(UserDTO dto, User entity) {
        if (dto == null || entity == null) {
            return null;
        }
        entity.setUsername(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

        Role role = new Role();
        dtoToEntity(dto.getRoles(), role);
        entity.setRoles(role);

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

        RoleDTO roleDto = new RoleDTO();
        entityToDto(entity.getRoles(), roleDto);
        dto.setRoles(roleDto);

        return dto;
    }

    @Override
    public Role dtoToEntity(RoleDTO dto, Role entity) {
        if (dto == null || entity == null) {
            return null;
        }
        entity.setId(dto.getId());
        entity.setRoleName(dto.getRoleName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    @Override
    public RoleDTO entityToDto(Role entity, RoleDTO dto) {
        if (dto == null || entity == null) {
            return null;
        }
        dto.setId(entity.getId());
        dto.setRoleName(entity.getRoleName());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}