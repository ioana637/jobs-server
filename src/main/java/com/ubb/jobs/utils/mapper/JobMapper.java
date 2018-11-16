package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.model.Job;
import com.ubb.jobs.model.JobAbility;
import com.ubb.jobs.model.Level;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface JobMapper {

//    @Mappings({
//            @Mapping(target = "abilities", expression = "java(abilitiesToIds(jobDto.getAbilities()))")
//    })
    Job toEntity(JobDto jobDto);
//    @Mappings({
//            @Mapping(target = "abilities", expression = "java(idsToAbilities(job.getAbilities()))")
//    })
    JobDto toDto (Job job);

    List<Job> toEntities(List<JobDto> dtos);
    List<JobDto> toDtos(List<Job> entities);


    default List<AbilityDto> idsToAbilities(List<JobAbility> ids) {
        AbilityMapper mapper = Mappers.getMapper(AbilityMapper.class);
        return ids == null ? null : ids.stream().map(id->mapper.toDtos(id.getAbility())).collect(Collectors.toList());
    }

//    default List<AbilityDto> idsToAbilities(Map<Integer, JobAbility> ids) {
//        return ids.keySet().stream().map(integer -> {
//            AbilityDto abilityDto = new AbilityDto();
//            abilityDto.setId(String.valueOf(integer));
//            return abilityDto;
//        }).collect(Collectors.toList());
//    }

    default List<JobAbility> abilitiesToIds(List<AbilityDto> abilities) {
        AbilityMapper mapper = Mappers.getMapper(AbilityMapper.class);
        return abilities == null ? null : abilities.stream().map(abilityDto -> {
            JobAbility jobAbility = new JobAbility();
            jobAbility.setLevel(Level.valueOf(abilityDto.getLevel()));
        jobAbility.setAbility(mapper.toEntity(abilityDto));
        return jobAbility;}).collect(Collectors.toList());
    }
}

