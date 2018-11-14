package com.ubb.jobs.controller;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.service.JobService;
import com.ubb.jobs.utils.constants.EndPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(EndPoint.JOB)
@Slf4j
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/{userId}/page?limit={pageSize}&start={start}")
    public ResponseEntity<List<JobDto>> getJobsPaginated(@RequestBody Integer userId, Integer pageSize, Integer start) {
        List<JobDto> jobs = jobService.findForClientId(userId, start, pageSize);
        if (jobs == null) {
            log.info("Unable to find any jobs for the given user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning " + pageSize + " starting from index " + start);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

}
