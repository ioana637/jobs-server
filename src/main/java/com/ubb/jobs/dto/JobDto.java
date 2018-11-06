package com.ubb.jobs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    private List<AbilityDto> abilities;

}
