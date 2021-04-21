package com.goku.api;

import com.goku.api.model.AddressDTO;
import com.goku.api.model.UserDTO;
import com.goku.service.AddressApiService;
import com.goku.service.UsersApiService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GokuApiController implements GokuApi {

    private final UsersApiService usersApiService;
    private final AddressApiService addressApiService;

    public GokuApiController(UsersApiService usersApiService, AddressApiService addressApiService) {
        this.usersApiService = usersApiService;
        this.addressApiService = addressApiService;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDTO body) {
        UserDTO user = usersApiService.createUser(body);
        if(user != null && user.getId() != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
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
    public ResponseEntity<AddressDTO> getAddress(@PathVariable("cep") String cep) {
        AddressDTO address = addressApiService.getAddress(cep);
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
