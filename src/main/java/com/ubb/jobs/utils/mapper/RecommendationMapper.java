package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.RecommendationDto;
import com.ubb.jobs.model.Recommendation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {
    @Mappings ({
        @Mapping(target = "recommender.id", source = "recommender"),
        @Mapping(target = "recommendedProvider.id", source = "recommendedProvider"),
        @Mapping(target = "userFor.id", source = "userFor")
    })
    RecommendationDto toDto(Recommendation entity);
    @Mappings({
    @Mapping(target = "recommender", source = "recommender.id"),
    @Mapping(target = "recommendedProvider", source = "recommendedProvider.id"),
    @Mapping(target = "userFor", source = "userFor.id")
    })
    Recommendation toEntity(RecommendationDto dto);


    List<Recommendation> toEntities(List<RecommendationDto> dtos);
    List<RecommendationDto> toDtos(List<Recommendation> entities);

}
