package com.goku.service.impl;

import com.goku.api.model.UserDTO;
import com.goku.domain.User;
import com.goku.mapper.UserMapper;
import com.goku.repository.UserRepository;
import com.goku.service.UsersApiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersApiServiceImpl implements UsersApiService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UsersApiServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO body) {
        User user = new User();
        userMapper.dtoToEntity(body, user);
        userRepository.save(user);
        userMapper.entityToDto(user, body);
        return body;
    }

    @Override
    public void updateUser(Long id, UserDTO dto) {
        User entity = userRepository.findById(id).orElse(null);
        if(entity != null){
            userMapper.dtoToEntity(dto, entity);
            userRepository.save(entity);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User entity = userRepository.findById(id).orElse(null);
        if(entity != null){
            UserDTO dto = new UserDTO();
            userMapper.entityToDto(entity, dto);
            return dto;
        }
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User entity = userRepository.findByUsername(username);
        if(entity != null){
            UserDTO dto = new UserDTO();
            userMapper.entityToDto(entity, dto);
            return dto;
        }
        return null;
    }

    @Override
    public List<UserDTO> getUserList() {
        List<UserDTO> dtoList = new ArrayList<>();
        Iterable<User> entityList = userRepository.findAll();
        for (User entity : entityList) {
            UserDTO dto = new UserDTO();
            userMapper.entityToDto(entity, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
