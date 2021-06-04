package com.example.login_register_demo.repository;

import com.example.login_register_demo.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityReporisoty extends CrudRepository<UserEntity,Long> {
    UserEntity findByUserName(String name);

}
