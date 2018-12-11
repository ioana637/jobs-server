package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.model.Job;
import com.ubb.jobs.repo.JpaJobRepo;
import com.ubb.jobs.repo.JpaUserRepo;
import com.ubb.jobs.utils.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
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

    public JobDto addJob(JobDto job) {
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

    public JobDto editJob(Integer id, JobDto newJob){
        Job job = jobRepo.getOne(id);
        job.setTitle(newJob.getTitle());
        job.setPeriodStart(newJob.getPeriodStart());
        job.setPeriodEnd(newJob.getPeriodEnd());
        job.setDescription(newJob.getDescription());
        job.setPeopleRequired(newJob.getPeopleRequired());
        job.setStatus(newJob.getStatus());
        job.setStartTime(newJob.getStartTime());
        job.setEndTime(newJob.getEndTime());
        job.setHoursPerDay(newJob.getHoursPerDay());
        job.setHoursPerWeek(newJob.getHoursPerWeek());
        job.setJobAbilities(newJob.getJobAbilities());
        return jobMapper.toDto(job);
    }
}
