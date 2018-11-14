package com.ubb.jobs.service;


import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.repo.impl.AbilityRepo;
import com.ubb.jobs.repo.impl.JobRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import com.ubb.jobs.utils.mapper.AbilityMapper;
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

    @Autowired
    private AbilityRepo abilityRepo;

    public List<JobDto> findAll() {
        List<JobDto> dtos = jobRepo.findAll();
        return dtos;
    }

    public List<JobDto> findForClientId(Integer id, int pageNumber, int pageSize) {
        List<JobDto> dtos = jobRepo.getForClientIdPaginated(id, pageNumber, pageSize);
        dtos.stream().map(job -> job.getAbilities().stream().map(abilityDto -> {return abilityRepo.getAbilityById(Integer.valueOf(abilityDto.getId()));}).collect(Collectors.toList())).collect(Collectors.toList());
        return dtos;
    }


}
