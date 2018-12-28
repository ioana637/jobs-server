package com.ubb.jobs.service;

import com.ubb.jobs.dto.*;
import com.ubb.jobs.model.Ability;
import com.ubb.jobs.model.Recommendation;
import com.ubb.jobs.repo.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Component
public class RecommendationService {
    @Autowired
    private RecommendationRepo recommendationRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private AbilityRepo abilityRepo;

    @Autowired
    private UserAbilityRepo userAbilityRepo;

    public RecommendationDto add(RecommendationDto dto) {
        RecommendationDto saved =  recommendationRepo.addRecommendation(dto);
        UserDto provider = userRepo.getOne(Integer.valueOf(saved.getRecommendedProvider().getId()));
        provider.setPassword(null);
        UserDto recommender = userRepo.getOne(Integer.valueOf(saved.getRecommender().getId()));
        recommender.setPassword(null);
        UserDto userFor = userRepo.getOne(Integer.valueOf(saved.getRecommender().getId()));
        saved.setUserFor(userFor);
        saved.setRecommendedProvider(provider);
        saved.setRecommender(recommender);
        return saved;
    }

    public List<RecommendationDto>getRecommandationReceived(Integer userFor){
        List<RecommendationDto>recommendationDtos=recommendationRepo.findRecommendationReceived(userFor);
        return buildDto(recommendationDtos);
    }
    private Double calculateMeanStars(Integer userId) {
        List<ReviewDto> reviews = reviewRepo.findReviewsForUser(userId);
        OptionalDouble mean =  reviews.stream().mapToDouble(review-> Double.valueOf(review.getStars())).average();
        return mean.isPresent() ? mean.getAsDouble() : null;
    }


    private List<RecommendationDto> buildDto(List<RecommendationDto> recommendationDtos) {
        return recommendationDtos.stream().map(dto-> {
            UserDto provider = userRepo.getOne(Integer.valueOf(dto.getRecommendedProvider().getId()));
            provider.setPassword(null);
            List<UserAbilitiesDto> userAbilitiesDto = userAbilityRepo.findAllByUser(Integer.valueOf(provider.getId()));
            List<AbilityDto> abilities = userAbilitiesDto.stream().map(ua-> {
                AbilityDto ability = ua.getAbility();
                ability.setLevel(ua.getLevel());
                return ability;
            }).collect(Collectors.toList());
            Double meanStars = calculateMeanStars(Integer.valueOf(provider.getId()));
            provider.setAbilities(abilities);
            provider.setStarAvg(meanStars == null ? null : String.valueOf(meanStars));
            UserDto recommender = userRepo.getOne(Integer.valueOf(dto.getRecommender().getId()));
            recommender.setPassword(null);
            UserDto userFor = userRepo.getOne(Integer.valueOf(dto.getUserFor().getId()));
            userFor.setPassword(null);
            dto.setRecommendedProvider(provider);
            dto.setRecommender(recommender);
            dto.setUserFor(userFor);
            return dto;
        }).collect(Collectors.toList());
    }

    public List<RecommendationDto>getRecommandationGiven(Integer recommender){
        List<RecommendationDto>recommendationDtos=recommendationRepo.findRecommendationGiven(recommender);
        return buildDto(recommendationDtos);
    }
}
