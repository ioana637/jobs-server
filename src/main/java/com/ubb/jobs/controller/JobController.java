package com.ubb.jobs.controller;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.service.JobService;
import com.ubb.jobs.utils.constants.EndPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/all")
    public ResponseEntity<List<JobDto>> getAllJobs() {
        List<JobDto> jobs = jobService.getAll();
        if (jobs == null) {
            log.info("Unable to find any jobs");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jobs, HttpStatus.OK);
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

    @GetMapping("/categories={categoriesList}")
    public ResponseEntity<List<JobDto>> getJobsByCategory(@PathVariable("categoriesList") List<String> categories){
        List<JobDto> jobs = jobService.getJobsByCategory(categories);
        if (jobs == null) {
            log.info("Unable to find any jobs for this categories");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("Returning " + jobs.size());
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/lastNJobs={lastNJobs}")
    public ResponseEntity<List<JobDto>> getLastNJobs(@PathVariable("lastNJobs") Integer lastN){
        List<JobDto> jobs = jobService.getLastNJobs(lastN);
        if (jobs == null) {
            log.info("Unable to find any jobs for this categories");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning " + jobs.size());
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
    @PostMapping("/{id}/assign")
    public ResponseEntity<JobDto> addWorkers(@PathVariable Integer id, @RequestBody List<String> users) {
        JobDto updated = jobService.assignEmployees(id, users);
        if (updated == null) {
            log.info("Something went wrong while adding employees");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobDto>> getJobsForEmployee(@PathVariable Integer userId) {
        List<JobDto> jobs = jobService.getJobForEmployee(userId);
        if (jobs == null) {
            log.info("Something went wrong");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jobs, HttpStatus.OK);

    }



}
