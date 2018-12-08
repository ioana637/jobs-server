package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.model.Ability;
import com.ubb.jobs.repo.JpaAbilityRepo;
import com.ubb.jobs.utils.mapper.AbilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AbilityRepo {

    @Autowired
    private AbilityMapper mapper;

    @Autowired
    private JpaAbilityRepo repo;

    public AbilityDto getAbilityById(Integer id) {
        Ability ability = repo.getAbilityById(id);
        return mapper.toDtos(ability);
    }

    public List<AbilityDto> saveAll(List<AbilityDto> abilities) {
        List<Ability> saved = repo.saveAll(mapper.toEntities(abilities));
        return mapper.toDtos(saved);
    }

    public List<AbilityDto> findAll() {
        List<Ability> abilities = repo.findAll();
        return mapper.toDtos(abilities);
    }
}
