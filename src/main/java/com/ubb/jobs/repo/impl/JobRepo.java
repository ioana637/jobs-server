package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Job;
import com.ubb.jobs.repo.JpaJobRepo;
import com.ubb.jobs.utils.mapper.JobMapper;
import com.ubb.jobs.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JobRepo {

    @Autowired
    private JpaJobRepo jobRepo;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private JpaJobRepo jpaJobRepo;

    public List<JobDto> findAll() {
        List<Job> jobs = jobRepo.findAll();

        return jobMapper.toDtos(jobs);
    }

    public List<JobDto> getJobsByCategory(List<String> categories){
        List<Job> jobs = jobRepo.findJobsByCategoryIn(categories);
        return jobMapper.toDtos(jobs);
    }

    public List<JobDto> getLastNJobs(Integer lastN){
        List<Job> allJobs = jobRepo.findAll();
        Collections.sort(allJobs, (o1, o2) -> {

            LocalDateTime date1 = o1.getDate();
            LocalDateTime date2 = o2.getDate();

            return date2.compareTo(date1);
        });

        if(allJobs.size()<=lastN){
            return jobMapper.toDtos(allJobs);
        }
        else {
            List<Job> firstNElementsList = allJobs.stream().limit(lastN).collect(Collectors.toList());

            return jobMapper.toDtos(firstNElementsList);
        }
    }

    public JobDto addJob(JobDto dto, UserDto userDto) {
        Job temp = jobMapper.toEntity(dto);
        temp.setIdClient(userMapper.toEntity(userDto));
//        Job saved = jobRepo.save(jobMapper.toEntity(dto));
        Job saved = jobRepo.save(temp);
        return jobMapper.toDto(saved);
    }

    public JobDto save(JobDto job) {
        Job saved = jobRepo.save(jobMapper.toEntity(job));
        return jobMapper.toDto(saved);

    }

    public List<JobDto> getForClientIdPaginated(Integer clientId, int pageNumber, int pageSize) {
        Page<Job> jobs = jobRepo.findAllByIdClient_Id(clientId, PageRequest.of(pageNumber, pageSize));
        return jobMapper.toDtos(jobs.getContent());
    }

    public JobDto getOne(Integer id) {
        Job job = jobRepo.getOne(id);
        return jobMapper.toDto(job);
    }

    public List<JobDto> saveAll(List<JobDto> jobs) {
        List<Job> saved = jobRepo.saveAll(jobMapper.toEntities(jobs));
        return jobMapper.toDtos(saved);
    }
    public List<JobDto> getByEmployee(UserDto userDto) {
        List<Job> jobs = jobRepo.findJobsByProvidersContains(userMapper.toEntity(userDto));
        return jobMapper.toDtos(jobs);
    }

    public JobDto findJobById(Integer id) {
        return jobMapper.toDto(jpaJobRepo.findJobById(id));
    }

    public int getNoContracts() {
        return (int)jobRepo.findAll()
                .stream()
                .filter(job -> job.getProviders() != null && job.getProviders().size() >= 1)
                .count();
    }

}
