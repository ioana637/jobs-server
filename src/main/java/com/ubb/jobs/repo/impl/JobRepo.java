package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.model.Job;
import com.ubb.jobs.repo.JpaJobRepo;
import com.ubb.jobs.repo.JpaUserRepo;
import com.ubb.jobs.utils.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobRepo {

    @Autowired
    private JpaJobRepo jobRepo;

    @Autowired
    private JobMapper jobMapper;

    public List<JobDto> findAll() {
        List<Job> jobs = jobRepo.findAll();

        return jobMapper.toDtos(jobs);
    }


    // the parameter will most likely be changed, based on the needs from the front-end
    public List<JobDto> getForClientId(Integer clientId) {
        List<Job> jobs = jobRepo.findJobsByIdClient(clientId);
        return jobMapper.toDtos(jobs);
    }
}
