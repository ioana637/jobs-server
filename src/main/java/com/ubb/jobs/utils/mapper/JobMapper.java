package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.model.Ability;
import com.ubb.jobs.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface JobMapper {

    @Mappings({
            @Mapping(target = "abilities", expression = "java(abilitiesToIds(jobDto.getAbilities()))")
    })
    Job toEntity(JobDto jobDto);
    @Mappings({
            @Mapping(target = "abilities", expression = "java(idsToAbilities(job.getAbilities()))")
    })
    JobDto toDto (Job job);

    List<Job> toEntities(List<JobDto> dtos);
    List<JobDto> toDtos(List<Job> entities);

    default List<AbilityDto> idsToAbilities(List<Integer> ids) {
        return ids.stream().map(id-> {
            AbilityDto abilityDto = new AbilityDto();
            abilityDto.setId(id.toString());
            return abilityDto;
        }).collect(Collectors.toList());
    }

    default List<Integer> abilitiesToIds(List<AbilityDto> abilities) {
        return abilities.stream().map(abilityDto ->  Integer.valueOf(abilityDto.getId())).collect(Collectors.toList());
    }
}

