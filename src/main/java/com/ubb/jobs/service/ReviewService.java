package com.ubb.jobs.service;
import com.ubb.jobs.dto.ReviewDto;

import com.ubb.jobs.repo.impl.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


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

    public boolean delete(Integer reviewId){
        return reviewRepo.deleteReview(reviewId);
    }

    public ReviewDto getOne(Integer id){
        return reviewRepo.getOne(id);
    }

    public List<ReviewDto> getReviewsOfUser(Integer userId){

        List<ReviewDto> dtos = reviewRepo.findAll();
        dtos =  dtos.stream().filter(review -> review.getUser()==userId)
        .collect(Collectors.toList());
        return dtos;
    }

}
