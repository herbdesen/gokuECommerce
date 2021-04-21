package com.goku.service.impl;

import com.goku.api.model.UserDTO;
import com.goku.domain.User;
import com.goku.repository.UserRepository;
import com.goku.service.UsersApiService;
import org.springframework.stereotype.Service;

@Service
public class UsersApiServiceImpl implements UsersApiService {

    private final UserRepository userRepository;

    public UsersApiServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO body) {
        User user = new User();
        user.setUsername(body.getUsername());
        user.setPassword(body.getPassword());
        user.setFirstName(body.getFirstName());
        user.setLastName(body.getLastName());

        User save = userRepository.save(user);
        body.setId(save.getId());
        return body;
    }
}
