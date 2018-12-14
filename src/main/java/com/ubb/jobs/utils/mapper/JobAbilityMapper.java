package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.JobAbilityDto;
import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.model.Job;
import com.ubb.jobs.model.JobAbility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobAbilityMapper {

    @Mapping(target = "job", expression = "java(dtoToJob(dto.getJob()))")
    JobAbility toEntity(JobAbilityDto dto);
    @Mapping(target = "job", expression = "java(jobToDto(entity.getJob()))")
    JobAbilityDto toDto(JobAbility entity);
    List<JobAbility> toEntities(List<JobAbilityDto> dtos);
    List<JobAbilityDto> toDtos(List<JobAbility> entities);

    default JobDto jobToDto(Job job) {
        JobMapper mapper = Mappers.getMapper(JobMapper.class);
        return mapper.toDto(job);
    }

    default Job dtoToJob(JobDto job) {
        JobMapper mapper = Mappers.getMapper(JobMapper.class);
        return mapper.toEntity(job);
    }

}
