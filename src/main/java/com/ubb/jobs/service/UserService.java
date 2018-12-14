package com.ubb.jobs.service;

import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Role;
import com.ubb.jobs.model.User;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReviewService reviewService;


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

    public List<UserDto> findProvidersPaginated( int pageNumber, int pageSize) {
        List<UserDto> dtos = userRepo.findProvidersPaginated(Role.PROVIDER, pageNumber, pageSize);
        dtos.stream().map(userDto -> {
            double stars = reviewService.getMeanOfStarsforUser(Integer.valueOf(userDto.getId()));
            userDto.setStarAvg(String.valueOf(stars));
            userDto.setPassword(null);
            userDto.setPostalCode(null);
            userDto.setAddress(null);
            userDto.setBirthDate(null);
            userDto.setSubscribed(null);
            userDto.setFacebook(null);
            userDto.setInstagram(null);
            userDto.setTwitter(null);
            return userDto;
        }).collect(Collectors.toList());
        return dtos;
    }

    public List<UserDto> findAllClients(){
        List<UserDto> dtos = userRepo.findClients(Role.CLIENT);
        return dtos;
    }

}
