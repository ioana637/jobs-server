package com.ubb.jobs.controller;


import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.service.StatisticsService;
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
@RequestMapping(EndPoint.STATISTICS)
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/clients")
    public ResponseEntity<Double> getStatsClients(){
        Double mean= statisticsService.statsClients();
        return  new ResponseEntity<>(mean, HttpStatus.OK);
    }

    @GetMapping("/providers")
    public ResponseEntity<Double> getStatsProviders(){
        Double mean= statisticsService.statsProviders();
        return  new ResponseEntity<>(mean, HttpStatus.OK);
    }

    @GetMapping("/allJobs")
    public ResponseEntity<List<JobDto>> getAllJobs() {
        List<JobDto> jobs = statisticsService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/availableJobs")
    public ResponseEntity<List<JobDto>> getAvailableJobs() {
        List<JobDto> jobs = statisticsService.getAvailableJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

}
