package com.ubb.jobs.service;


import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.dto.JobAbilityDto;
import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.JobStatus;
import com.ubb.jobs.repo.impl.AbilityRepo;
import com.ubb.jobs.repo.impl.JobAbilityRepo;
import com.ubb.jobs.repo.impl.JobRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    public List<JobDto> findForClientId(Integer id, int pageNumber, int pageSize) {
        List<JobDto> dtos = jobRepo.getForClientIdPaginated(id, pageNumber, pageSize);
        dtos =  dtos.stream().map(job -> {

               job.setAbilities(job.getAbilities().stream().map(abilityDto ->
                        abilityRepo.getAbilityById(Integer.valueOf(abilityDto.getId()))).collect(Collectors.toList()));
        return job;
        }).collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public JobDto add(JobDto dto) {
        if (dto.getId() != null) {
            jobAbilityRepo.removeByJobId(Integer.valueOf(dto.getId()));
        }
        List<AbilityDto> abilityDtos = abilityRepo.saveAll(dto.getAbilities());
        for (int i = 0; i < abilityDtos.size(); i++)
            abilityDtos.get(i).setLevel(dto.getAbilities().get(i).getLevel());
        LocalDateTime localDateTime = LocalDateTime.now();
        dto.setDate(localDateTime.toString());
        dto.setAbilities(null);
        dto.setStatus("AVAILABLE");
        JobDto saved =  jobRepo.save(dto);
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

    public JobDto getJobById(Integer id) {
        JobDto job = jobRepo.findJobById(id);
        job.getAbilities().forEach(ability -> {
            String level =  jobAbilityRepo.getOneByAbilityAndJob(Integer.valueOf(ability.getId()), Integer.valueOf(job.getId())).getLevel().toLowerCase();
            ability.setLevel(level);
        });
        job.getProviders().forEach(provider-> {
            provider.setPassword(null);
        });
        return job;
    }

    public JobDto assignEmployees(Integer id, List<String> employees) {
        JobDto job = jobRepo.findJobById(id);
        Set<UserDto> users = employees.stream().map(unique -> userRepo.getOne(Integer.valueOf(unique))).collect(Collectors.toSet());
        job.setAbilities(null);
        job.setProviders(null);
        jobRepo.save(job);
        job.setProviders(users);
        job = jobRepo.save(job);
        job.setProviders(job.getProviders().stream().map(provider-> {
            provider.setPassword(null);
            return provider;
        }).collect(Collectors.toSet()));
        return job;
    }
}
