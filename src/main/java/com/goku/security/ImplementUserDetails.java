package com.goku.security;

import com.goku.entity.RoleEntity;
import com.goku.entity.UserEntity;
import com.goku.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplementUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public ImplementUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.findByUsername(userEmail);

        if (userEntity == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        List<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(userEntity.getRoles());

        return org.springframework.security.core.userdetails.User.withUsername(userEmail)
                .password(userEntity.getPassword()).authorities(roleEntities).accountExpired(false).accountLocked(false)
                .credentialsExpired(false).disabled(false).build();
    }
}
