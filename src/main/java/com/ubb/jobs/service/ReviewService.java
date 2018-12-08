package com.ubb.jobs.service;
import com.ubb.jobs.dto.ReviewDto;

import com.ubb.jobs.repo.impl.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


@Component
public class ReviewService {
    @Autowired
    ReviewRepo reviewRepo;


    public List<ReviewDto> getReviewsOrdered(){
        List<ReviewDto> dtos = reviewRepo.getOrderedReviews();
        return dtos;
    }
    public ReviewDto add(ReviewDto dto) {
       ReviewDto reviewDto = reviewRepo.addReview(dto);
       return reviewDto;
    }

    @Transactional
    public Integer delete(Integer reviewId){
        return reviewRepo.deleteReview(reviewId);
    }

    public ReviewDto getOne(Integer id){
        return reviewRepo.getOne(id);
    }

    public List<ReviewDto> getReviewsOfUser(Integer userId){

        List<ReviewDto> dtos = reviewRepo.findForUser(userId);
        return dtos;
    }

    public List<ReviewDto> getReviewsForUser(Integer idUserFor){
        List<ReviewDto> dtos = reviewRepo.findReviewsForUser(idUserFor);
        return dtos;
    }

    public double getMeanOfStarsforUser(Integer idUserFor){
        List<ReviewDto> dtos = reviewRepo.findReviewsForUser(idUserFor);
        double mean=0;
        for(ReviewDto dto :dtos)
            mean+=Double.valueOf(dto.getStars());
        return mean/dtos.size();
    }

}
