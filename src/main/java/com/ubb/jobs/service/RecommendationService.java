package com.ubb.jobs.service;

import com.ubb.jobs.dto.RecommendationDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.repo.impl.RecommendationRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
