package com.ubb.jobs.service;

import com.ubb.jobs.dto.RecommendationDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Recommendation;
import com.ubb.jobs.repo.impl.RecommendationRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        saved.setRecommendedProvider(provider);
        saved.setRecommender(recommender);
        return saved;
    }

    public List<RecommendationDto>getRecommandationReceived(Integer recommenderProvider){
        List<RecommendationDto>recommendationDtos=recommendationRepo.findRecommendationReceived(recommenderProvider);
        for(RecommendationDto r : recommendationDtos) {
            UserDto provider = userRepo.getOne(Integer.valueOf(r.getRecommendedProvider().getId()));
            provider.setPassword(null);
            UserDto recommender = userRepo.getOne(Integer.valueOf(r.getRecommender().getId()));
            recommender.setPassword(null);
            r.setRecommendedProvider(provider);
            r.setRecommender(recommender);
        }
        return recommendationDtos;
    }
    public List<RecommendationDto>getRecommandationGiven(Integer recommender){
        List<RecommendationDto>recommendationDtos=recommendationRepo.findRecommendationGiven(recommender);
        for(RecommendationDto r : recommendationDtos) {
            UserDto provider = userRepo.getOne(Integer.valueOf(r.getRecommendedProvider().getId()));
            provider.setPassword(null);
            UserDto recommender1 = userRepo.getOne(Integer.valueOf(r.getRecommender().getId()));
            recommender1.setPassword(null);
            r.setRecommendedProvider(provider);
            r.setRecommender(recommender1);
        }
        return recommendationDtos;
    }
}
