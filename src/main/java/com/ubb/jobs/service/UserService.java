package com.ubb.jobs.service;

import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.User;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDto login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepo.getByUsernameAndPassword(user);
    }

    public UserDto add(UserDto dto) {
        UserDto saved =  userRepo.addUser(dto);
        return saved;
    }

    public List<UserDto> findForClientId(Integer id, int pageNumber, int pageSize) {
        List<UserDto> dtos = userRepo.getForClientIdPaginated(id, pageNumber, pageSize);
        return dtos;
    }

}
