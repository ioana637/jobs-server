package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.*;
import com.ubb.jobs.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface JobMapper {

    @Mappings({
            @Mapping(target = "abilities", expression = "java(abilitiesToIds(jobDto.getAbilities()))"),
            @Mapping(target = "providers", expression = "java(usersToEntity(jobDto.getProviders()))"),
            @Mapping(target = "requests", expression= "java(req2Entity(jobDto.getRequests()))"),
            @Mapping(target = "usersReviewed", expression= "java(rev2Entity(jobDto.getUsersReviewed()))"),
            @Mapping(target = "idClient.id", source = "idClient")

    })
    Job toEntity(JobDto jobDto);
        @Mappings({
                @Mapping(target = "abilities", expression = "java(idsToAbilities(job.getAbilities()))"),
                @Mapping(target = "providers", expression = "java(usersToDto(job.getProviders()))"),
                @Mapping(target = "usersReviewed", expression= "java(rev2Dto(job.getUsersReviewed()))"),
                @Mapping(target = "requests", expression= "java(req2Dto(job.getRequests()))"),
                @Mapping(target = "idClient", source = "idClient.id")
        })
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
            jobAbility.setLevel(abilityDto.getLevel() == null ? null : Level.valueOf(abilityDto.getLevel()));
        jobAbility.setAbility(mapper.toEntity(abilityDto));
        return jobAbility;}).collect(Collectors.toList());
    }

    default Set<UserDto> usersToDto(Set<User> users) {
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        return userMapper.setDtos(users);
//        return new HashSet<>(userMapper.toDtos(new ArrayList<>(users)));
    }
    default Set<User> usersToEntity(Set<UserDto> users) {
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        return userMapper.setEntities(users);
//        return new HashSet<>(userMapper.toEntities(new ArrayList<>(users)));

     }

    default List<RequestDto> req2Dto(List<Request> req) {
        RequestMapper mapper = Mappers.getMapper(RequestMapper.class);
        return mapper.toDtos(req);
    }

    default List<Request> req2Entity(List<RequestDto> req) {
        RequestMapper mapper = Mappers.getMapper(RequestMapper.class);
        return mapper.toEntities(req);
    }

    default Set<ReviewDto> rev2Dto(Set<Review> req) {
        ReviewMapper mapper = Mappers.getMapper(ReviewMapper.class);
        return mapper.toSetDtos(req);
    }

    default Set<Review> rev2Entity(Set<ReviewDto> req) {
        ReviewMapper mapper = Mappers.getMapper(ReviewMapper.class);
        return mapper.toSetEntities(req);
    }
}

