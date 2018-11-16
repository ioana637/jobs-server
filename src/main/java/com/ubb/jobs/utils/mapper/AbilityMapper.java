package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.model.Ability;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbilityMapper {

    Ability toEntity(AbilityDto dto);
    AbilityDto toDtos(Ability entity);
    List<Ability> toEntities(List<AbilityDto> dtos);
    List<AbilityDto> toDtos(List<Ability> entities);

}
