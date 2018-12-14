package com.ubb.jobs.service;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.dto.UserAbilitiesDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Level;
import com.ubb.jobs.model.UserAbilities;
import com.ubb.jobs.repo.impl.AbilityRepo;
import com.ubb.jobs.repo.impl.UserAbilityRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AbilityService {

    @Autowired
    private AbilityRepo abilityRepo;

    @Autowired
    private UserAbilityRepo userAbilityRepo;

    @Autowired
    private UserRepo userRepo;

    public List<AbilityDto> findAll() {
        List<AbilityDto> dtos = abilityRepo.findAll();
        return dtos;
    }

    public List<Level> findAllAbilitiesLevels() {
        return Arrays.asList(Level.values());
    }

    public UserAbilitiesDto save(UserAbilitiesDto userAbilitiesDto) {
        AbilityDto abilityDto = abilityRepo.getAbilityById(Integer.valueOf(userAbilitiesDto.getAbility().getId()));
        userAbilitiesDto.setAbility(abilityDto);
        UserDto userDto = userRepo.getOne(Integer.valueOf(userAbilitiesDto.getUser().getId()));
        userAbilitiesDto.setUser(userDto);
        userAbilitiesDto =  userAbilityRepo.save(userAbilitiesDto);
        userAbilitiesDto.getUser().setPassword(null);
        return userAbilitiesDto;
    }

    public List<AbilityDto> getAbilitiesForUser(Integer userId) {
        List<UserAbilitiesDto> dtos =  userAbilityRepo.findAllByUser(userId);
        return dtos.stream().map(dto-> {
            AbilityDto abilityDto = new AbilityDto();
            abilityDto.setLevel(dto.getLevel());
            abilityDto.setId(dto.getAbility().getId());
            abilityDto.setCode(dto.getAbility().getCode());
            abilityDto.setDisplay(dto.getAbility().getDisplay());
            return abilityDto;
        }).collect(Collectors.toList());
    }
}
