package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);
    UserDto toDto (User user);
}
