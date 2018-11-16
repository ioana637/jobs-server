package com.ubb.jobs.service;


import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.dto.JobAbilityDto;
import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.repo.impl.AbilityRepo;
import com.ubb.jobs.repo.impl.JobAbilityRepo;
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

    @Autowired
    private JobAbilityRepo jobAbilityRepo;

    @Autowired
    private AbilityRepo abilityRepo;

    public List<JobDto> findAll() {
        List<JobDto> dtos = jobRepo.findAll();
        return dtos;
    }

    public List<JobDto> findForClientId(Integer id, int pageNumber, int pageSize) {
        List<JobDto> dtos = jobRepo.getForClientIdPaginated(id, pageNumber, pageSize);
        dtos =  dtos.stream().map(job -> {

               job.setAbilities(job.getAbilities().stream().map(abilityDto ->
                        abilityRepo.getAbilityById(Integer.valueOf(abilityDto.getId()))).collect(Collectors.toList()));
        return job;
        }).collect(Collectors.toList());
        return dtos;
    }

    public JobDto add(JobDto dto) {
        List<AbilityDto> abilityDtos = abilityRepo.saveAll(dto.getAbilities());
        dto.setAbilities(null);
        JobDto saved =  jobRepo.addJob(dto);
        List<JobAbilityDto> jobAbilityDtos = abilityDtos.stream().map(abilityDto -> {
            JobAbilityDto jobAbilityDto = new JobAbilityDto();
            jobAbilityDto.setAbility(abilityDto);
            jobAbilityDto.setJob(saved);
            jobAbilityDto.setLevel(abilityDto.getLevel());
            return jobAbilityDto;
        }).collect(Collectors.toList());
        jobAbilityRepo.saveAll(jobAbilityDtos);
        return saved;
    }

}
