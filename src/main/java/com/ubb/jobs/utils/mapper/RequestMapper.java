package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.RequestDto;
import com.ubb.jobs.model.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    @Mappings({
            @Mapping(target = "idUserFrom", source = "userFrom.id"),
            @Mapping(target = "idUserTo", source = "userTo.id"),
            @Mapping(target = "idJob", source = "job.id")
    })
    Request toEntity(RequestDto dto);

    @Mappings({
            @Mapping(target = "userFrom.id", source = "idUserFrom"),
            @Mapping(target = "userTo.id", source = "idUserTo"),
            @Mapping(target = "job.id", source = "idJob")
    })
    RequestDto toDto(Request entity);

    List<Request> toEntities(List<RequestDto> dtos);
    List<RequestDto> toDtos(List<Request> entities);


}
