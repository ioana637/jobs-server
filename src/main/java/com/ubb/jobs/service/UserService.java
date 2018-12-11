package com.ubb.jobs.service;

import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Role;
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

    public List<UserDto> findProvidersPaginated(String role, int pageNumber, int pageSize) {
        List<UserDto> dtos = userRepo.findProvidersPaginated(Role.valueOf(role), pageNumber, pageSize);
        // TODO remove unnecessary attributes such as password, birth date, facebook, twitter, address
        // TODO you can to this by using maps, you have an example in RequestService, createDtos method
        return dtos;
    }

}
