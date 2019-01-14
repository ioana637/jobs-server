package com.ubb.jobs.service;

import com.ubb.jobs.dto.*;
import com.ubb.jobs.model.Job;
import com.ubb.jobs.model.Role;
import com.ubb.jobs.model.User;
import com.ubb.jobs.repo.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private UserAbilityRepo userAbilityRepo;

    @Autowired
    private AbilityRepo abilityRepo;

    @Autowired
    private JobRepo jobRepo;

    @Transactional
    public UserDto login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepo.getByUsernameAndPassword(user);

    }

    @Transactional
    public UserDto add(UserDto dto) {
        if (dto.getId() != null) {
            userAbilityRepo.removeAllByUserId(Integer.valueOf(dto.getId()));
            UserDto user = userRepo.getOne(Integer.valueOf(dto.getId()));
            dto.setRecommendations(user.getRecommendations());
            dto.setReviewsReceived(user.getReviewsReceived());
            dto.setReviewsMade(user.getReviewsMade());
            dto.setRecommendationsProvider(user.getRecommendationsProvider());
            dto.setRequestsMade(user.getRequestsMade());
            dto.setRequestsReceived(user.getRequestsReceived());
            dto.setUserFor(user.getUserFor());
        }
        List<AbilityDto> abilityDtos = abilityRepo.saveAll(dto.getAbilities());
        for (int i = 0; i < abilityDtos.size(); i++)
            abilityDtos.get(i).setLevel(dto.getAbilities().get(i).getLevel());
        dto.setAbilities(null);
        UserDto saved =  userRepo.addUser(dto);
        List<UserAbilitiesDto> userAbilitiesDtos= abilityDtos.stream().map(abilityDto -> {
            UserAbilitiesDto userAbilitiesDto= new UserAbilitiesDto();
            userAbilitiesDto.setAbility(abilityDto);
            userAbilitiesDto.setUser(saved);
            userAbilitiesDto.setLevel(abilityDto.getLevel());
            return userAbilitiesDto;
        }).collect(Collectors.toList());
        userAbilityRepo.saveAll(userAbilitiesDtos);
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

    public List<UserDto> getProvidersByRating(Float starAvg){
        List<UserDto> dtos = userRepo.findProviders(Role.PROVIDER);
        dtos = dtos.stream().map(dto-> {
            List<ReviewDto> reviewDtos = reviewRepo.findReviewsForUser(Integer.valueOf(dto.getId()));
            dto.setStarAvg(String.valueOf(reviewDtos.stream().mapToDouble(review-> Float.valueOf(review.getStars())).average().orElse(0.0)));
            return dto;
        }).filter(dto-> Float.valueOf(dto.getStarAvg()) >= starAvg).collect(Collectors.toList());
        return addAbilityToUsers(dtos);
    }

    public List<UserDto> getProvidersByAbilities(List<Integer> abilities){
        List<UserDto> dtos = userRepo.getProvidersByAbilities(Role.PROVIDER,abilities);
        return addAbilityToUsers(dtos);
    }

    public List<UserDto> getClientColaborators(Integer userId){
        UserDto provider=userRepo.getOne(userId);
        List<UserDto> clientColaborators=new ArrayList<>();
        List<JobDto> jobs=jobRepo.findAll();
        for (JobDto job:jobs) {
            if(job.getProviders().contains(provider))
                clientColaborators.add(userRepo.getOne(Integer.parseInt(job.getIdClient())));
        }
        return addAbilityToUsers(clientColaborators);
    }

}
