package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.*;
import com.ubb.jobs.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mappings({
            @Mapping(target = "requestsMade", expression= "java(req2Entity(dto.getRequestsMade()))"),
            @Mapping(target = "requestsReceived", expression= "java(req2Entity(dto.getRequestsReceived()))"),
            @Mapping(target = "recommendations", expression= "java(rec2Entity(dto.getRecommendations()))"),
            @Mapping(target = "recommendationsProvider", expression= "java(rec2Entity(dto.getRecommendationsProvider()))"),
//            @Mapping(target = "jobs", expression ="java(job2Entity(dto.getJobs()))" ),

            @Mapping(target = "reviewsMade", expression= "java(rev2Entity(dto.getReviewsMade()))"),
            @Mapping(target = "reviewsReceived", expression= "java(rev2Entity(dto.getReviewsReceived()))")
    })
    User toEntity(UserDto dto);

    @Mappings({
            @Mapping(target = "requestsMade", expression= "java(req2Dto(user.getRequestsMade()))"),
            @Mapping(target = "requestsReceived", expression= "java(req2Dto(user.getRequestsReceived()))"),
            @Mapping(target = "recommendations", expression= "java(rec2Dto(user.getRecommendations()))"),
            @Mapping(target = "recommendationsProvider", expression= "java(rec2Dto(user.getRecommendationsProvider()))"),
            @Mapping(target = "reviewsMade", expression= "java(rev2Dto(user.getReviewsMade()))"),
            //            @Mapping(target = "jobs", expression ="java(job2Dto(user.getJobs()))" ),

            @Mapping(target = "reviewsReceived", expression= "java(rev2Dto(user.getReviewsReceived()))")

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

    default List<ReviewDto> rev2Dto(List<Review> req) {
        ReviewMapper mapper = Mappers.getMapper(ReviewMapper.class);
        return mapper.toDtos(req);
    }

    default List<Review> rev2Entity(List<ReviewDto> req) {
        ReviewMapper mapper = Mappers.getMapper(ReviewMapper.class);
        return mapper.toEntities(req);
    }


//    default List<JobDto> job2Dto(List<Integer> req) {
//        return req == null ? null : req.stream().map(id->{
//            JobDto job = new JobDto();
//            job.setId(String.valueOf(id));
//            return job;
//        }).collect(Collectors.toList());
//    }
//
//    default List<Integer> job2Entity(List<JobDto> req) {
//        return req == null ? null : req.stream().map(job->Integer.valueOf(job.getId())).collect(Collectors.toList());
//    }
}
