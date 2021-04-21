package com.goku.mapper.impl;

import com.goku.api.model.AddressDTO;
import com.goku.domain.Address;
import com.goku.mapper.AddressMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address dtoToEntity(AddressDTO dto, Address entity) {
        if (dto == null || entity == null) {
            return null;
        }
        entity.setCep(dto.getCep());
        entity.setLogradouro(dto.getLogradouro());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        return entity;
    }

    @Override
    public AddressDTO entityToDto(Address entity, AddressDTO dto){
        if (dto == null || entity == null) {
            return null;
        }
        dto.setId(entity.getId());
        dto.setCep(entity.getCep());
        dto.setLogradouro(entity.getLogradouro());
        dto.setBairro(entity.getBairro());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        return dto;
    }
}