package com.goku.api;

import com.goku.api.model.AuthResponseDTO;
import com.goku.api.model.UserDTO;
import com.goku.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class AuthApiController implements AuthApi {

    private final AuthenticationService authenticationService;

    public AuthApiController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<AuthResponseDTO> authUser(@Valid @RequestBody UserDTO body){
        try{
            AuthResponseDTO authResponseDTO = authenticationService.authUser(body.getUsername(), body.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(authResponseDTO);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
