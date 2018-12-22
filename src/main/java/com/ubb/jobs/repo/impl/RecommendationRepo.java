package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.RecommendationDto;
import com.ubb.jobs.model.Recommendation;
import com.ubb.jobs.repo.JpaRecommendationRepo;
import com.ubb.jobs.utils.mapper.RecommendationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<RecommendationDto> findRecommendationReceived(Integer recommendedProvider){
        List<Recommendation> recommendations = recommendationRepo.findAllByRecommendedProvider(recommendedProvider);
        return recommendationMapper.toDtos(recommendations);
    }

    public List<RecommendationDto> findRecommendationGiven(Integer recommender){
        List<Recommendation> recommendations = recommendationRepo.findAllByRecommender(recommender);
        return recommendationMapper.toDtos(recommendations);
    }
}
