package com.ubb.jobs.controller;
import com.ubb.jobs.dto.ReviewDto;
import com.ubb.jobs.service.ReviewService;
import com.ubb.jobs.utils.constants.EndPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(EndPoint.REVIEW)
@Slf4j
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @DeleteMapping("/{reviewId}")
       public ResponseEntity<ReviewDto> delete(@PathVariable Integer reviewId) {
        if(reviewService.delete(reviewId)){
            log.info("Deleted review with id "+ reviewId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> save(@RequestBody ReviewDto review) {
        ReviewDto saved = reviewService.add(review);
        if (saved == null) {
            log.info("Unable to save the review");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<List<ReviewDto>> getAll(){
        List<ReviewDto> reviewDtos= reviewService.getReviewsOrdered();
        if(reviewDtos.isEmpty()) {
            log.info("No entries");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

}