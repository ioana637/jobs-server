package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.UserAbilitiesDto;
import com.ubb.jobs.model.UserAbilities;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAbilitiesMapper {

//    @Mappings({
//            @Mapping(target = "idUser", source = "user.id"),
//            @Mapping(target = "idAbility", source = "ability.id")
//    })
    UserAbilities toEntity(UserAbilitiesDto dto);
//    @Mappings({
//            @Mapping(target = "user.id", source = "idUser"),
//            @Mapping(target = "ability.id", source = "idAbility")
//    })
    UserAbilitiesDto toDto(UserAbilities entity);

    List<UserAbilities> toEntities(List<UserAbilitiesDto> dtos);
    List<UserAbilitiesDto> toDtos(List<UserAbilities> entities);
}
