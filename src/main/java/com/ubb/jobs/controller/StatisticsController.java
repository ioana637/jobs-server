package com.ubb.jobs.controller;


import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.dto.StatisticsDto;
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

    @GetMapping()
    public ResponseEntity<StatisticsDto> getStatistics() {
        try {
            StatisticsDto statisticsDto = statisticsService.getStatistics();
            return new ResponseEntity<>(statisticsDto, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
