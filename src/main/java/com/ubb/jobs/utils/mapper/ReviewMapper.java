package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.ReviewDto;
import com.ubb.jobs.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

//    @Mapping(target = "idUserFor", source = "userFor.id")
    Review toEntity(ReviewDto reviewDto);
//    @Mapping(target = "userFor.id", source = "idUserFor")
    ReviewDto toDto(Review entity);

    List<Review> toEntities(List<ReviewDto> dtos);
    List<ReviewDto> toDtos(List<Review> entities);

}
