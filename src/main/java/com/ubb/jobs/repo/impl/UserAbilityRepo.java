package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.UserAbilitiesDto;
import com.ubb.jobs.model.UserAbilities;
import com.ubb.jobs.repo.JpaUserAbilityRepo;
import com.ubb.jobs.utils.mapper.UserAbilitiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAbilityRepo {

    @Autowired
    private JpaUserAbilityRepo repo;

    @Autowired
    private UserAbilitiesMapper mapper;

    public UserAbilitiesDto save(UserAbilitiesDto userAbilitiesDto) {
        UserAbilities saved = repo.save(mapper.toEntity(userAbilitiesDto));
        return mapper.toDto(saved);
    }

    public List<UserAbilitiesDto> findAllByUser(Integer userId) {
        List<UserAbilities> dtos = repo.findAllByUser_Id(userId);
        return mapper.toDtos(dtos);
    }

}
