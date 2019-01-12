package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.ReviewDto;
import com.ubb.jobs.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mappings({
            @Mapping(target = "idUser", source = "userFrom.id"),
            @Mapping(target = "idUserFor", source = "userFor.id"),
            @Mapping(target = "jobId", source = "job.id")
    })

    Review toEntity(ReviewDto reviewDto);
    @Mappings({
            @Mapping(target = "userFrom.id", source = "idUser"),
            @Mapping(target = "userFor.id", source = "idUserFor"),
            @Mapping(target = "job.id", source = "jobId")
    })
    ReviewDto toDto(Review entity);

    List<Review> toEntities(List<ReviewDto> dtos);
    List<ReviewDto> toDtos(List<Review> entities);

    Set<Review> toSetEntities(Set<ReviewDto> dtos);
    Set<ReviewDto> toSetDtos(Set<Review> entities);

}
