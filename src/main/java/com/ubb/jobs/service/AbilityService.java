package com.ubb.jobs.service;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.repo.impl.AbilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AbilityService {

    @Autowired
    private AbilityRepo abilityRepo;

    public List<AbilityDto> findAll() {
        List<AbilityDto> dtos = abilityRepo.findAll();
        return dtos;
    }

}
