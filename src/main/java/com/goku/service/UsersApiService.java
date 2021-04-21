package com.goku.service;

import com.goku.api.model.UserDTO;

public interface UsersApiService {
    UserDTO createUser(UserDTO body);
}
