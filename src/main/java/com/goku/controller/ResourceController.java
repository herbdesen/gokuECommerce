package com.goku.controller;

import com.goku.domain.RandomCity;
import com.goku.domain.User;
import com.goku.service.GenericService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goku")
public class ResourceController {

    private final GenericService userService;

    public ResourceController(GenericService userService) {
        this.userService = userService;
    }

    @GetMapping(value ="/cities")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<RandomCity> getUser(){
        return userService.findAllRandomCities();
    }

    @GetMapping(value ="/users")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @Cacheable("users")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
}
