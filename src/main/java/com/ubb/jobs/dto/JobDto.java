package com.ubb.jobs.dto;

import lombok.Data;

@Data
public class JobDto {
    private UserDto client;

    private String periodStart;

    private String periodEnd;

    private String description;

    private Integer peopleRequired;

    private Boolean available;

    private String startTime;

    private String endTime;

    private Integer hoursPerDay;

    private Integer hoursPerWeek;

}
