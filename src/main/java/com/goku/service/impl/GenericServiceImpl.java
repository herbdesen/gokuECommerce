package com.goku.service.impl;

import com.goku.repository.RandomCityRepository;
import com.goku.repository.UserRepository;
import com.goku.domain.RandomCity;
import com.goku.domain.User;
import com.goku.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericServiceImpl implements GenericService {

    private final UserRepository userRepository;

    private final RandomCityRepository randomCityRepository;

    public GenericServiceImpl(UserRepository userRepository, RandomCityRepository randomCityRepository) {
        this.userRepository = userRepository;
        this.randomCityRepository = randomCityRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        System.out.println("aqui");
        return (List<User>)userRepository.findAll();
    }

    @Override
    public List<RandomCity> findAllRandomCities() {
        return (List<RandomCity>)randomCityRepository.findAll();
    }
}
