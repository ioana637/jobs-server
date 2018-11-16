package com.ubb.jobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAbilityDto {

    private String Id;

    private JobDto job;

    private AbilityDto ability;

    private String level;
}
