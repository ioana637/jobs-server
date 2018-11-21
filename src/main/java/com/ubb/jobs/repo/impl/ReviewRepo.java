package com.ubb.jobs.repo.impl;
import com.ubb.jobs.dto.ReviewDto;
import com.ubb.jobs.model.Review;
import com.ubb.jobs.repo.JpaReviewRepo;
import com.ubb.jobs.utils.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class ReviewRepo {
    @Autowired
    private JpaReviewRepo jpaReviewRepoR;

    @Autowired
    private ReviewMapper reviewMapper;


    public ReviewDto getOne(Integer id) {
        Review review = jpaReviewRepoR.getOne(id);
        return reviewMapper.toDto(review);
    }

    public List<ReviewDto> getOrderedReviews(){
        List<Review> reviews =jpaReviewRepoR.findAll(new Sort(Sort.Direction.DESC, "stars"));
        return reviewMapper.toDtos(reviews);
    }


    public ReviewDto addReview(ReviewDto review) {
        Review saved = jpaReviewRepoR.save(reviewMapper.toEntity(review));
        return reviewMapper.toDto(saved);
    }

    public boolean deleteReview(int reviewId){

        try{
            Review review =jpaReviewRepoR.getOne(reviewId);
            if(review.getId() == null)
                return false;
            jpaReviewRepoR.delete(review);
        }
        catch (javax.persistence.EntityNotFoundException ex){
            return false;
        }
            return true;
    }


    public List<ReviewDto> findAll(){
        List<Review> reviews = jpaReviewRepoR.findAll();
        return reviewMapper.toDtos(reviews);
    }
}
