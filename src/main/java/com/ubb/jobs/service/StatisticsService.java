package com.ubb.jobs.service;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Role;
import com.ubb.jobs.model.User;
import com.ubb.jobs.repo.impl.JobRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
public class StatisticsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JobRepo jobRepo;


    public double statsProviders(){
        int nr_providers =userRepo.findProviders(Role.PROVIDER).size();
        return 100*getNumberProviders()/nr_providers;
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

    public double statsClients(){
        int nr_clients= userRepo.findClients(Role.CLIENT).size();
        return 100*getNumberClients()/nr_clients;
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
}
