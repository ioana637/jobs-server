package com.ubb.jobs.service;


import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.repo.impl.JobRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private UserRepo userRepo;

    public List<JobDto> findAll() {
        List<JobDto> dtos = jobRepo.findAll();
        return dtos;
    }

    public List<JobDto> findForClientId(Integer id) {
        List<JobDto> dtos = jobRepo.getForClientId(id);

        return dtos;
    }


}
