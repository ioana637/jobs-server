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
import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

//    @Transactional
//    @Scheduled(fixedRate = 5000)
//    public void updateJobs() {
//        List<JobDto> jobs = jobRepo.findAll();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        jobs.forEach(job-> {
//            if (job.getPeriodEnd() != null) {
//                LocalDate endTime = LocalDate.parse(job.getPeriodEnd(), formatter);
//                LocalDate now = LocalDate.now();
//                if (endTime.isBefore(now)) {
//                    job.setStatus(JobStatus.EXPIRED.name());
//
//                }
//                job.setAbilities(null);
//                Set<UserDto> providers = job.getProviders();
//                job.setProviders(null);
//                jobRepo.save(job);
//                job.setProviders(providers);
//                jobRepo.save(job);
//
//            }
//        });
//
//    }

    public List<JobDto> findForClientId(Integer id, int pageNumber, int pageSize) {
        List<JobDto> dtos = jobRepo.getForClientIdPaginated(id, pageNumber, pageSize);
        return buildJobDto(dtos);

    }
    public List<JobDto> getAll() {
        List<JobDto> jobs = jobRepo.findAll();
        return buildJobDto(jobs);
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
        Set<UserDto> providers = dto.getProviders();
        dto.setProviders(null);
        JobDto saved =  jobRepo.save(dto);
        dto.setProviders(providers);
        jobRepo.save(dto);
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

    private boolean jobAvailable(JobDto job, List<String> employees) {
        if (Enum.valueOf(JobStatus.class, job.getStatus()).equals(JobStatus.EXPIRED))
            return false;
        return job.getProviders().size() + employees.size() <= Integer.valueOf(job.getPeopleRequired());
    }

    public JobDto assignEmployees(Integer id, List<String> employees) {
        JobDto job = jobRepo.findJobById(id);
        if (!jobAvailable(job, employees))
            return null;
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

    public List<JobDto> getJobsByCategory(List<String> categories){
        List<JobDto> dtos = jobRepo.getJobsByCategory(categories);


//        dtos =  dtos.stream().map(job -> {
//
//            job.setAbilities(job.getAbilities().stream().map(abilityDto ->
//                    abilityRepo.getAbilityById(Integer.valueOf(abilityDto.getId()))).collect(Collectors.toList()));
//            return job;
//        }).collect(Collectors.toList());

        return buildJobDto(dtos);
    }

    public List<JobDto> getLastNJobs(Integer lastN){
        List<JobDto> dtos = jobRepo.getLastNJobs(lastN);

        return buildJobDto(dtos);

    }

    private List<JobDto> buildJobDto(List<JobDto> dtos) {
        return dtos.stream().map(job -> {

            job.setAbilities(job.getAbilities().stream().map(abilityDto -> {
                JobAbilityDto jobAbilityDto = jobAbilityRepo.getOneByAbilityAndJob(Integer.valueOf(abilityDto.getId()), Integer.valueOf(job.getId()));
                abilityDto.setLevel(jobAbilityDto.getLevel());
                return abilityDto;
            }).collect(Collectors.toList()));
            job.setProviders(job.getProviders().stream().map(user->  {
                user.setPassword(null);
                return user;
            }).collect(Collectors.toSet()));
            return job;
        }).collect(Collectors.toList());
    }

    public List<JobDto> getJobForEmployee(Integer userId) {
        List<JobDto> jobs =  jobRepo.getByEmployee(userRepo.getOne(userId));
        return jobs.stream().map(job-> {
            job.setProviders(null);
            job.setAbilities(null);
            return job;
        }).collect(Collectors.toList());
    }
}
