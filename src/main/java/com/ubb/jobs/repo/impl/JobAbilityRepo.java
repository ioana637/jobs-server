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

    public JobAbilityDto getOneByAbilityAndJob(Integer abilityId, Integer jobId) {
        return mapper.toDto(repo.getJobAbilityByAbility_IdAndJob_Id(abilityId, jobId));
    }

    public List<JobAbilityDto> saveAll(List<JobAbilityDto> jobAbilityDtos) {
        List<JobAbility> saved = repo.saveAll(mapper.toEntities(jobAbilityDtos));
        return mapper.toDtos(saved);
    }

    public void removeByJobId(Integer integer) {
        int value = repo.removeAllByJob_Id(integer);
    }
}
