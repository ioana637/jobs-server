package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.model.Ability;
import com.ubb.jobs.repo.JpaAbilityRepo;
import com.ubb.jobs.utils.mapper.AbilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbilityRepo {

    @Autowired
    private AbilityMapper mapper;

    @Autowired
    private JpaAbilityRepo repo;

    public AbilityDto getAbilityById(Integer id) {
        Ability ability =  repo.getOne(id);
        return mapper.toDto(ability);
    }

}
