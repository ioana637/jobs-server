package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.User;
import com.ubb.jobs.repo.JpaUserRepo;
import com.ubb.jobs.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepo {

    @Autowired
    private JpaUserRepo jpaUserRepo;

    @Autowired
    private UserMapper userMapper;

    // Example on how to get data from repository

    public UserDto getUser(User user) {
        User received = jpaUserRepo.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        return userMapper.toDto(received);
    }
}
