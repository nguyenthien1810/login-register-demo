package com.example.login_register_demo.service;

import com.example.login_register_demo.model.UserEntity;
import com.example.login_register_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    @Autowired
    private UserRepository userRepository ;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(UserEntity userEntity) {
        userEntity.setRoleName("ROLE_USER");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userEntity.setPassWord(encoder.encode(userEntity.getPassWord()));
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findByName(String name) {
        return userRepository.findByName(name);
    }
}
