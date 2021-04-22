package com.goku.service.impl;

import com.goku.api.model.AddressDTO;
import com.goku.domain.Address;
import com.goku.mapper.AddressMapper;
import com.goku.repository.AddressRepository;
import com.goku.service.AddressApiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressApiServiceImpl implements AddressApiService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressApiServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDTO createAddress(AddressDTO dto) {
        Address entity = new Address();
        addressMapper.dtoToEntity(dto, entity);
        addressRepository.save(entity);
        addressMapper.entityToDto(entity, dto);
        return dto;
    }

    @Override
    public void updateAddress(Long id, AddressDTO dto) {
        Address entity = addressRepository.findById(id).orElse(null);
        if(entity != null){
            addressMapper.dtoToEntity(dto, entity);
            addressRepository.save(entity);
        }
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public AddressDTO getAddressByCep(String cep) {
        Address entity = addressRepository.findByCep(cep);
        if(entity != null){
            AddressDTO dto = new AddressDTO();
            addressMapper.entityToDto(entity, dto);
            return dto;
        }
        return null;
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        Address entity = addressRepository.findById(id).orElse(null);
        if(entity != null){
            AddressDTO dto = new AddressDTO();
            addressMapper.entityToDto(entity, dto);
            return dto;
        }
        return null;
    }

    @Override
    public List<AddressDTO> getAddressList() {
        List<AddressDTO> dtoList = new ArrayList<>();
        Iterable<Address> entityList = addressRepository.findAll();
        for (Address entity : entityList) {
            AddressDTO dto = new AddressDTO();
            addressMapper.entityToDto(entity, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

}
