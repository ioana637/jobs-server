package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.RecommendationDto;
import com.ubb.jobs.model.Recommendation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {

    @Mapping(target = "recommender.id", source = "recommender")
    @Mapping(target = "recommendedProvider.id", source = "recommendedProvider")
    RecommendationDto toDto(Recommendation entity);
    @Mapping(target = "recommender", source = "recommender.id")
    @Mapping(target = "recommendedProvider", source = "recommendedProvider.id")
    Recommendation toEntity(RecommendationDto dto);


    List<Recommendation> toEntities(List<RecommendationDto> dtos);
    List<RecommendationDto> toDtos(List<Recommendation> entities);

}
