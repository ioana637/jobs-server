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

@CrossOrigin
@RestController
@Slf4j
@RequestMapping(EndPoint.RECOMMENDATION)
public class RecommendationController {
    @Autowired
    private RecommendationService service;

    @PostMapping
    public ResponseEntity<RecommendationDto> save(@RequestBody RecommendationDto user) {
        RecommendationDto saved = service.add(user);
        if (saved == null) {
            log.info("Unable to send this recommendation!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
}
