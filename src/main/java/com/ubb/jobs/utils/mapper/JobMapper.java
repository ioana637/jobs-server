package com.ubb.jobs.utils.mapper;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.model.Job;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {

    // TODO finish mapping these, and start working on the other mappers
    Job toEntity(JobDto jobDto);
    JobDto toDto (Job job);

    List<Job> toEntities()
}
