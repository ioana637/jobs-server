package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Role;
import com.ubb.jobs.model.User;
import com.ubb.jobs.repo.JpaUserRepo;
import com.ubb.jobs.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepo {

    @Autowired
    private JpaUserRepo jpaUserRepo;

    @Autowired
    private UserMapper userMapper;

    // Example on how to get data from repository

    public UserDto getByUsernameAndPassword(User user) {
        User received = jpaUserRepo.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        return userMapper.toDto(received);
    }

    public UserDto getOne(Integer id) {
        User user = jpaUserRepo.getOne(id);
        return userMapper.toDto(user);
    }

    public UserDto addUser(UserDto user) {
        User saved = jpaUserRepo.save(userMapper.toEntity(user));
        return userMapper.toDto(saved);

    }

    public List<UserDto> findProvidersPaginated(Role role, int pageNumber, int pageSize) {
        Page<User> users = jpaUserRepo.findAllByRoleEquals(role, PageRequest.of(pageNumber, pageSize));
        return userMapper.toDtos(users.getContent());
    }


    public List<UserDto> findProviders(Role role) {

    public List<UserDto> findClients(Role role) {

        List<User> users = jpaUserRepo.findAllByRole(role);
        return userMapper.toDtos(users);
    }
}
