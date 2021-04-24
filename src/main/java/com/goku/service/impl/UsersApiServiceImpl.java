package com.goku.service.impl;

import com.goku.api.model.UserDTO;
import com.goku.entity.UserEntity;
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
        UserEntity userEntity = new UserEntity();
        userMapper.dtoToEntity(body, userEntity);
        userRepository.save(userEntity);
        userMapper.entityToDto(userEntity, body);
        return body;
    }

    @Override
    public void updateUser(Long id, UserDTO dto) {
        UserEntity entity = userRepository.findById(id).orElse(null);
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
        UserEntity entity = userRepository.findById(id).orElse(null);
        if(entity != null){
            UserDTO dto = new UserDTO();
            userMapper.entityToDto(entity, dto);
            return dto;
        }
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        UserEntity entity = userRepository.findByUsername(username);
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
        Iterable<UserEntity> entityList = userRepository.findAll();
        for (UserEntity entity : entityList) {
            UserDTO dto = new UserDTO();
            userMapper.entityToDto(entity, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
