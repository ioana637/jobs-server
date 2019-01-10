package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.dto.RecommendationDto;
import com.ubb.jobs.dto.RequestDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Job;
import com.ubb.jobs.model.Recommendation;
import com.ubb.jobs.model.Request;
import com.ubb.jobs.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {



    @Mappings({
            @Mapping(target = "requestsMade", expression= "java(req2Entity(dto.getRequestsMade()))"),
            @Mapping(target = "requestsReceived", expression= "java(req2Entity(dto.getRequestsReceived()))"),
            @Mapping(target = "recommendations", expression= "java(rec2Entity(dto.getRecommendations()))"),
            @Mapping(target = "recommendationsProvider", expression= "java(rec2Entity(dto.getRecommendationsProvider()))"),
            @Mapping(target = "jobs", expression ="java(job2Entity(dto.getJobs()))" )

    })
    User toEntity(UserDto dto);
    @Mappings({
            @Mapping(target = "requestsMade", expression= "java(req2Dto(user.getRequestsMade()))"),
            @Mapping(target = "requestsReceived", expression= "java(req2Dto(user.getRequestsReceived()))"),
            @Mapping(target = "recommendations", expression= "java(rec2Dto(user.getRecommendations()))"),
            @Mapping(target = "recommendationsProvider", expression= "java(rec2Dto(user.getRecommendationsProvider()))"),
            @Mapping(target = "jobs", expression ="java(job2Dto(user.getJobs()))" )
    })
    UserDto toDto (User user);
    List<User> toEntities(List<UserDto> dtos);
    List<UserDto> toDtos(List<User> entities);

    Set<User> setEntities(Set<UserDto> dtos);
    Set<UserDto> setDtos(Set<User> entities);

    default List<RequestDto> req2Dto(List<Request> req) {
        RequestMapper mapper = Mappers.getMapper(RequestMapper.class);
        return mapper.toDtos(req);
    }

    default List<Request> req2Entity(List<RequestDto> req) {
        RequestMapper mapper = Mappers.getMapper(RequestMapper.class);
        return mapper.toEntities(req);
    }

    default List<RecommendationDto> rec2Dto(List<Recommendation> req) {
        RecommendationMapper mapper = Mappers.getMapper(RecommendationMapper.class);
        return mapper.toDtos(req);
    }

    default List<Recommendation> rec2Entity(List<RecommendationDto> req) {
        RecommendationMapper mapper = Mappers.getMapper(RecommendationMapper.class);
        return mapper.toEntities(req);
    }

    default List<JobDto> job2Dto(List<Job> req) {
        JobMapper mapper = Mappers.getMapper(JobMapper.class);
        return mapper.toDtos(req);
    }

    default List<Job> job2Entity(List<JobDto> req) {
        JobMapper mapper = Mappers.getMapper(JobMapper.class);
        return mapper.toEntities(req);
    }

}
