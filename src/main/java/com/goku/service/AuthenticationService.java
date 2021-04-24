package com.goku.service;

import com.goku.api.model.AuthResponseDTO;

public interface AuthenticationService {
    AuthResponseDTO authUser(String username, String password) throws Exception;
}
