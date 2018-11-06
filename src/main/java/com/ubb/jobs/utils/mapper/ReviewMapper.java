package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.ReviewDto;
import com.ubb.jobs.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "idUser", source = "user.id")
    Review toEntity(ReviewDto reviewDto);
    @Mapping(target = "user.id", source = "idUser")
    ReviewDto toDto(Review entity);

    List<Review> toEntities(List<ReviewDto> dtos);
    List<ReviewDto> toDtos(List<Review> entities);

}
