package com.goku.api;

import com.goku.api.model.AddressDTO;
import com.goku.service.AddressApiService;
import com.goku.service.UsersApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressApiController implements AddressApi {

    private final UsersApiService usersApiService;
    private final AddressApiService addressApiService;

    public AddressApiController(UsersApiService usersApiService, AddressApiService addressApiService) {
        this.usersApiService = usersApiService;
        this.addressApiService = addressApiService;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> createAddress(@Valid @RequestBody AddressDTO body) {
        AddressDTO address = addressApiService.createAddress(body);
        if(address != null && address.getId() != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> updateAddress(@PathVariable("id") Long id, @Valid @RequestBody AddressDTO body) {
        addressApiService.updateAddress(id, body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
        addressApiService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<AddressDTO> getAddressByCep(@PathVariable("cep") String cep) {
        AddressDTO address = addressApiService.getAddressByCep(cep);
        if(address != null){
            return new ResponseEntity<>(address, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable("id") Long id) {
        AddressDTO address = addressApiService.getAddressById(id);
        if(address != null){
            return new ResponseEntity<>(address, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<AddressDTO>> getAddressList() {
        List<AddressDTO> addressList = addressApiService.getAddressList();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

}
