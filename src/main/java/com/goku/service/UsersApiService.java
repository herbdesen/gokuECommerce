package com.goku.service;

import com.goku.api.model.UserDTO;

import java.util.List;

public interface UsersApiService {
    UserDTO createUser(UserDTO body);

    void updateUser(Long id, UserDTO dto);

    void deleteUser(Long id);

    UserDTO getUserById(Long id);

    List<UserDTO> getUserList();
}
