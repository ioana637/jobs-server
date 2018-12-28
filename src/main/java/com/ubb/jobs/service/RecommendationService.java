package com.ubb.jobs.service;

import com.ubb.jobs.dto.RecommendationDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Recommendation;
import com.ubb.jobs.repo.impl.RecommendationRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecommendationService {
    @Autowired
    private RecommendationRepo recommendationRepo;

    @Autowired
    private UserRepo userRepo;

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

    public List<RecommendationDto>getRecommandationReceived(Integer recommenderProvider){
        List<RecommendationDto>recommendationDtos=recommendationRepo.findRecommendationReceived(recommenderProvider);
        return buildDto(recommendationDtos);
    }

    private List<RecommendationDto> buildDto(List<RecommendationDto> recommendationDtos) {
        return recommendationDtos.stream().map(dto-> {
            UserDto provider = userRepo.getOne(Integer.valueOf(dto.getRecommendedProvider().getId()));
            provider.setPassword(null);
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
