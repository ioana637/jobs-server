package com.ubb.jobs.service;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.dto.StatisticsDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.JobStatus;
import com.ubb.jobs.model.Role;
import com.ubb.jobs.repo.impl.JobRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StatisticsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JobRepo jobRepo;

    public StatisticsDto getStatistics() {
        int nrOfProviders = userRepo.findProviders(Role.PROVIDER).size();
        double providersWithJobPercentage = 100*getNumberProviders()/nrOfProviders;

        int nrClients= userRepo.findClients(Role.CLIENT).size();
        double clientsWithJobPercentage = 100*getNumberClients()/nrClients;

        int usersWithMaxRating = userRepo.getUsersWithMaxRatingCount();

        int noOfContracts = jobRepo.getNoContracts();

        int noOfAvailableJobs = getAvailableJobs().size();

        return new StatisticsDto(providersWithJobPercentage, nrOfProviders,
                clientsWithJobPercentage, usersWithMaxRating, noOfContracts, noOfAvailableJobs);
    }

    private double getNumberProviders(){
        Set<UserDto> providers= new HashSet<>();
        List<JobDto> jobs= jobRepo.findAll();
        for( JobDto job : jobs){
            Iterator iter = job.getProviders().iterator();
            while (iter.hasNext())
                providers.add((UserDto) iter.next());
        }
        return providers.size();

    }

    private double getNumberClients(){
        Set<String> clients= new HashSet<>();
        List<JobDto> jobs= jobRepo.findAll();
        for( JobDto job : jobs){
            if (job.getProviders().size()>0)
                clients.add(job.getIdClient());
        }
        return clients.size();
    }

    public List<JobDto> getAvailableJobs() {
        List<JobDto> jobs = jobRepo.findAll();
        List<JobDto> available = new ArrayList<>();
        for (JobDto job : jobs) {
            if (JobStatus.valueOf(job.getStatus()).equals(JobStatus.AVAILABLE))
                available.add(job);
        }

        return available;
    }
}
