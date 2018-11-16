package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.JobAbilityDto;
import com.ubb.jobs.model.JobAbility;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobAbilityMapper {

    JobAbility toEntity(JobAbilityDto dto);
    JobAbilityDto toDto(JobAbility entity);
    List<JobAbility> toEntities(List<JobAbilityDto> dtos);
    List<JobAbilityDto> toDtos(List<JobAbility> entities);

}
