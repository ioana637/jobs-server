package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.model.Ability;
import com.ubb.jobs.model.Job;
import com.ubb.jobs.model.JobAblitiesRelation;
import com.ubb.jobs.model.Level;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Map;
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

//    default List<AbilityDto> idsToAbilities(Map<Integer, JobAblitiesRelation> ids) {
//        return ids.keySet().stream().map(integer -> {
//            AbilityDto abilityDto = new AbilityDto();
//            abilityDto.setId(String.valueOf(integer));
//            return abilityDto;
//        }).collect(Collectors.toList());
//    }

//    default Map<Integer, JobAblitiesRelation> abilitiesToIds(List<AbilityDto> abilities) {
////        return abilities.stream().collect(Collectors.toMap(key-> Integer.valueOf(key.getId()), value -> new JobAblitiesRelation(Level.valueOf(value.getLevel()))));
//    }
}

