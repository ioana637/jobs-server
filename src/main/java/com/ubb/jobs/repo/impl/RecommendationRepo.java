package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.RecommendationDto;
import com.ubb.jobs.model.Recommendation;
import com.ubb.jobs.repo.JpaRecommendationRepo;
import com.ubb.jobs.utils.mapper.RecommendationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecommendationRepo {
    @Autowired
    private JpaRecommendationRepo recommendationRepo;

    @Autowired
    private RecommendationMapper recommendationMapper;

    public RecommendationDto addRecommendation(RecommendationDto recommendation) {
        Recommendation saved = recommendationRepo.save(recommendationMapper.toEntity(recommendation));
        return recommendationMapper.toDto(saved);

    }
}
