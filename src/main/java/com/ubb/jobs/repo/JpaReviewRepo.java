package com.ubb.jobs.repo;

import com.ubb.jobs.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewRepo extends JpaRepository<Review, Integer> {
}
