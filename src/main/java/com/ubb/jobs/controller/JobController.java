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

    @GetMapping("/id={jobId}")
    public ResponseEntity<JobDto> getJobById(@PathVariable("jobId") Integer jobId) {

        JobDto job = jobService.getJobById(jobId);
        if (job == null) {
            log.info("Unable to find any job with id: " + jobId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning job with id: " + jobId);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/user={userId}&limit={pageSize}&start={pageNumber}")
    public ResponseEntity<List<JobDto>> getJobsPaginated(@PathVariable("userId") Integer userId, @PathVariable("pageSize") Integer pageSize, @PathVariable("pageNumber") Integer pageNumber) {
        List<JobDto> jobs = jobService.findForClientId(userId, pageNumber, pageSize);
        if (jobs == null) {
            log.info("Unable to find any jobs for the given user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning " + jobs.size() + " from page " + pageNumber);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobDto> save(@RequestBody JobDto job) {
        JobDto saved = jobService.add(job);
        if (saved == null) {
            log.info("Unable to find any jobs for the given user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobDto> editJob(@RequestBody Integer id,@RequestBody JobDto newJob) {
        JobDto job = jobService.editJob(id,newJob);
        if (job == null) {
            log.info("Unable to edit the job");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Job edited " + job);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
}
