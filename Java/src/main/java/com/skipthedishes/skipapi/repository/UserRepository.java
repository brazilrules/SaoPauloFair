package com.skipthedishes.skipapi.repository;

import com.skipthedishes.skipapi.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
    public UserEntity findUserByUserNameAndUserPassword(String userName, String userPassword);
}
