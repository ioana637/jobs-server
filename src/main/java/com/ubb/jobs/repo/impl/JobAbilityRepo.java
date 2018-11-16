package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.JobAbilityDto;
import com.ubb.jobs.model.JobAbility;
import com.ubb.jobs.repo.JpaJobAbilityRepo;
import com.ubb.jobs.utils.mapper.JobAbilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobAbilityRepo {

    @Autowired
    private JpaJobAbilityRepo repo;

    @Autowired
    private JobAbilityMapper mapper;

    public List<JobAbilityDto> saveAll(List<JobAbilityDto> jobAbilityDtos) {
        List<JobAbility> saved = repo.saveAll(mapper.toEntities(jobAbilityDtos));
        return mapper.toDtos(saved);
    }

}
