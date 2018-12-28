package com.ubb.jobs.controller;

import com.ubb.jobs.dto.RecommendationDto;
import com.ubb.jobs.model.Recommendation;
import com.ubb.jobs.service.RecommendationService;
import com.ubb.jobs.utils.constants.EndPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping(EndPoint.RECOMMENDATION)
public class RecommendationController {
    @Autowired
    private RecommendationService service;

    @PostMapping
    public ResponseEntity<RecommendationDto> save(@RequestBody RecommendationDto recommendation) {
        RecommendationDto saved = service.add(recommendation);
        if (saved == null) {
            log.info("Unable to send this recommendation!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @GetMapping("/recommended/{userFor}")
    public ResponseEntity<List<RecommendationDto>> getRecommendationReceived(@PathVariable("userFor") Integer userFor) {
        List<RecommendationDto> recommendationDtos = service.getRecommandationReceived(userFor);
        if (recommendationDtos == null) {
            log.info("Unable to find any recommendations for the given user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning " + recommendationDtos.size());
        return new ResponseEntity<>(recommendationDtos, HttpStatus.OK);
    }
    @GetMapping("/recommender/{recommender}")
    public ResponseEntity<List<RecommendationDto>> getRecommendationGiven(@PathVariable("recommender") Integer recommender) {
        List<RecommendationDto> recommendationDtos = service.getRecommandationGiven(recommender);
        if (recommendationDtos == null) {
            log.info("Unable to find any recommendations for the given user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning " + recommendationDtos.size());
        return new ResponseEntity<>(recommendationDtos, HttpStatus.OK);
    }
}
