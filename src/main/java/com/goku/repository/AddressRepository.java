package com.goku.repository;

import com.goku.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

    Address findByCep(String cep);

}
