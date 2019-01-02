package com.ubb.jobs.service;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.dto.ReviewDto;
import com.ubb.jobs.dto.UserAbilitiesDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Role;
import com.ubb.jobs.model.User;
import com.ubb.jobs.repo.impl.ReviewRepo;
import com.ubb.jobs.repo.impl.UserAbilityRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private UserAbilityRepo userAbilityRepo;

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

    public UserDto getUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDto loggedUser = userRepo.getByUsernameAndPassword(user);
        return addAbilityToUsers(Arrays.asList(loggedUser)).get(0);
    }

    private Double calculateMeanStars(Integer userId) {
        List<ReviewDto> reviews = reviewRepo.findReviewsForUser(userId);
        OptionalDouble mean =  reviews.stream().mapToDouble(review-> Double.valueOf(review.getStars())).average();
        return mean.isPresent() ? mean.getAsDouble() : null;
    }

    public List<UserDto> findProvidersPaginated( int pageNumber, int pageSize) {
        List<UserDto> dtos = userRepo.findProvidersPaginated(Role.PROVIDER, pageNumber, pageSize);
        return addAbilityToUsers(dtos);
    }

    private List<UserDto> addAbilityToUsers(List<UserDto> dtos) {
        return dtos.stream().map(userDto -> {
            List<UserAbilitiesDto> userAbilitiesDtos = userAbilityRepo.findAllByUser(Integer.valueOf(userDto.getId()));
            List<AbilityDto> abilities = userAbilitiesDtos.stream().map(userAbility-> {
                AbilityDto abilityDto = userAbility.getAbility();
                abilityDto.setLevel(userAbility.getLevel());
                return abilityDto;
            }).collect(Collectors.toList());
            Double stars = calculateMeanStars(Integer.valueOf(userDto.getId()));
            userDto.setStarAvg(stars == null ? null : String.valueOf(stars));
            userDto.setPassword(null);
            userDto.setPostalCode(null);
            userDto.setAddress(null);
            userDto.setBirthDate(null);
            userDto.setSubscribed(null);
            userDto.setFacebook(null);
            userDto.setInstagram(null);
            userDto.setTwitter(null);
            userDto.setAbilities(abilities);
            return userDto;
        }).collect(Collectors.toList());
    }

    public List<UserDto> findAllClients(){
        List<UserDto> dtos = userRepo.findClients(Role.CLIENT);
        return addAbilityToUsers(dtos);

    }

    public List<UserDto> findProviders(){
        List<UserDto>dtos=userRepo.findProviders(Role.PROVIDER);
        return addAbilityToUsers(dtos);
    }

}
