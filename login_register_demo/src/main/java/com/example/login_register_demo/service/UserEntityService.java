package com.example.login_register_demo.service;

import com.example.login_register_demo.model.UserEntity;

import java.util.List;

public interface UserEntityService {
    List<UserEntity> findAll();

    void  deleteById(Long id);

    UserEntity findUserById(Long id);

    void save(UserEntity userEntity);

    List<UserEntity> findByName(String name);
}
