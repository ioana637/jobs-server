package com.ubb.jobs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class StatisticsDto {

    public StatisticsDto(Double providersWithJobPercantage, int noOfProviders,
                         Double clientsWithJobPercentage, int clientsWithMaxRating,
                         int noOfContracts, int noOfAvailableJobs) {
        this.providersWithJobPercantage = providersWithJobPercantage;
        this.noOfProviders = noOfProviders;
        this.clientsWithJobPercentage = clientsWithJobPercentage;
        this.usersWithMaxRating = clientsWithMaxRating;
        this.noOfContracts = noOfContracts;
        this.noOfAvailableJobs = noOfAvailableJobs;
    }

    private Double providersWithJobPercantage;
    private int noOfProviders;
    private Double clientsWithJobPercentage;
    private int usersWithMaxRating;
    private int noOfContracts;
    private int noOfAvailableJobs;
}
