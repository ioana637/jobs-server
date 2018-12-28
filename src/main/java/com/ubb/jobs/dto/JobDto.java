package com.ubb.jobs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobDto {

    private String id;

    private String title;

    private String idClient;

    private String periodStart;

    private String periodEnd;

    private String description;

    private String peopleRequired;

    private String startTime;

    private String endTime;

    private String hoursPerDay;

    private String hoursPerWeek;

    private String status;

    private String category;

    private String location;

    private List<AbilityDto> abilities;

    private Set<UserDto> providers;

    private String date;
}
