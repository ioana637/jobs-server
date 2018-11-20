package com.ubb.jobs.service;

import com.ubb.jobs.dto.RecommendationDto;
import com.ubb.jobs.repo.impl.RecommendationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecommendationService {
    @Autowired
    private RecommendationRepo recommendationRepo;

    public RecommendationDto add(RecommendationDto dto) {
        RecommendationDto saved =  recommendationRepo.addRecommendation(dto);
        return saved;
    }
}
