package com.goku.service;

import com.goku.api.model.AddressDTO;

import java.util.List;

public interface AddressApiService {
    AddressDTO createAddress(AddressDTO body);

    void updateAddress(Long is, AddressDTO body);

    void deleteAddress(Long id);

    AddressDTO getAddressByCep(String cep);

    AddressDTO getAddressById(Long id);

    List<AddressDTO> getAddressList();
}
