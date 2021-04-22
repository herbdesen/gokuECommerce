package com.goku.mapper.impl;

import com.goku.api.model.RoleDTO;
import com.goku.api.model.UserDTO;
import com.goku.domain.Role;
import com.goku.domain.User;
import com.goku.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        List<Role> roleList = new ArrayList<>();
        for(RoleDTO roleDto : dto.getRoles()){
            Role role = new Role();
            roleList.add(dtoToEntity(roleDto, role));
        }
        entity.setRoles(roleList);

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

        List<RoleDTO> roleDtoList = new ArrayList<>();
        for(Role role : entity.getRoles()){
            RoleDTO roleDto = new RoleDTO();
            roleDtoList.add(entityToDto(role, roleDto));
        }
        dto.setRoles(roleDtoList);

        return dto;
    }

    @Override
    public Role dtoToEntity(RoleDTO dto, Role entity) {
        if (dto == null || entity == null) {
            return null;
        }
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