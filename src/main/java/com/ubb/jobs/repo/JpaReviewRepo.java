package com.ubb.jobs.repo;

import com.ubb.jobs.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface JpaReviewRepo extends JpaRepository<Review, Integer> {

    List<Review> findAllByIdUser(Integer idUser);

    Integer removeById(Integer reviewId);

    List<Review> findAllByIdUserFor(Integer idUserFor);

    Set<Review> findAllByJobId(Integer jobId);
}
