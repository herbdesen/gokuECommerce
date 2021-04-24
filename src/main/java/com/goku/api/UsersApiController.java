package com.goku.api;

import com.goku.api.model.UserDTO;
import com.goku.service.UsersApiService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UsersApiController implements UsersApi {

    private final UsersApiService usersApiService;

    public UsersApiController(UsersApiService usersApiService) {
        this.usersApiService = usersApiService;
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDTO body) {
        UserDTO user = usersApiService.createUser(body);
        if(user != null && user.getId() != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDTO body) {
        usersApiService.updateUser(id, body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        usersApiService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Cacheable("users")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        UserDTO user = usersApiService.getUserById(id);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @Cacheable("users")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
        UserDTO user = usersApiService.getUserByUsername(username);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @Cacheable("users")
    public ResponseEntity<List<UserDTO>> getUserList() {
        List<UserDTO> userList = usersApiService.getUserList();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}
