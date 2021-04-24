package com.goku.service.impl;

import com.goku.api.model.AuthDTO;
import com.goku.api.model.AuthResponseDTO;
import com.goku.repository.UserRepository;
import com.goku.security.AuthJwtTokenProvider;
import com.goku.service.AuthenticationService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthJwtTokenProvider authJwtTokenProvider;

    public AuthenticationServiceImpl(UserRepository userRepository, AuthJwtTokenProvider authJwtTokenProvider) {
        this.userRepository = userRepository;
        this.authJwtTokenProvider = authJwtTokenProvider;
    }

    @Override
    public AuthResponseDTO authUser(String username, String password) throws Exception {
        try {

            String token = authJwtTokenProvider.generateAuthToken(username, userRepository.findByUsername(username).getRoles());

            AuthDTO authDTO = new AuthDTO();
            authDTO.setType("Bearer");
            authDTO.setToken("Bearer " + token);

            AuthResponseDTO authResponseDTO = new AuthResponseDTO();
            authResponseDTO.setCode("success-authenticated");
            authResponseDTO.setMessage("User successfully authenticated");
            authResponseDTO.setData(authDTO);

            return authResponseDTO;

        } catch (AuthenticationException e) {
            throw new Exception("Usuário e/ou senha inválido(s)");
        }
    }
}
