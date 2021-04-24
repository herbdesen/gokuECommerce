package com.goku.repository;

import com.goku.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

    AddressEntity findByCep(String cep);

}
