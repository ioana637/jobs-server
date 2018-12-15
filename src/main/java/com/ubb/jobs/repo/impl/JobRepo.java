package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.model.Job;
import com.ubb.jobs.repo.JpaJobRepo;
import com.ubb.jobs.utils.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobRepo {

    @Autowired
    private JpaJobRepo jobRepo;

    @Autowired
    private JobMapper jobMapper;


    @Autowired
    private JpaJobRepo jpaJobRepo;

    public List<JobDto> findAll() {
        List<Job> jobs = jobRepo.findAll();

        return jobMapper.toDtos(jobs);
    }

    public JobDto save(JobDto job) {
        Job saved = jobRepo.save(jobMapper.toEntity(job));
        return jobMapper.toDto(saved);

    }

    public List<JobDto> getForClientIdPaginated(Integer clientId, int pageNumber, int pageSize) {
        Page<Job> jobs = jobRepo.findAllByIdClient(clientId, PageRequest.of(pageNumber, pageSize));
        return jobMapper.toDtos(jobs.getContent());
    }

    public JobDto getOne(Integer id) {
        Job job = jobRepo.getOne(id);
        return jobMapper.toDto(job);
    }

    public JobDto findJobById(Integer id) {
        return jobMapper.toDto(jpaJobRepo.findJobById(id));
    }


}
