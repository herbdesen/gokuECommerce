package com.goku.mapper;

import com.goku.api.model.AddressDTO;
import com.goku.entity.AddressEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AddressEntity dtoToEntity(AddressDTO dto, AddressEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AddressDTO entityToDto(AddressEntity entity, AddressDTO dto);
}
