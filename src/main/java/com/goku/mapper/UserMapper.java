package com.goku.mapper;

import com.goku.api.model.RoleDTO;
import com.goku.api.model.UserDTO;
import com.goku.entity.RoleEntity;
import com.goku.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity dtoToEntity(UserDTO dto, UserEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserDTO entityToDto(UserEntity entity, UserDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RoleEntity dtoToEntity(RoleDTO dto, RoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RoleDTO entityToDto(RoleEntity entity, RoleDTO dto);

}
