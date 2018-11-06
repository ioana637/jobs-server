package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);
    UserDto toDto (User user);
    List<User> toEntities(List<UserDto> dtos);
    List<UserDto> toDtos(List<User> entities);

}
