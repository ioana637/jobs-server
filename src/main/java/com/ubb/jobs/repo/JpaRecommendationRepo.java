package com.ubb.jobs.repo;

import com.ubb.jobs.model.Recommendation;
import com.ubb.jobs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRecommendationRepo extends JpaRepository<Recommendation, Integer> {

    List<Recommendation> findAllByRecommendedProvider(Integer recommendedProvider);
    List<Recommendation> findAllByRecommender(Integer recommender);
}


