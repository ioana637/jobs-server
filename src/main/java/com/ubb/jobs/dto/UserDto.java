package com.ubb.jobs.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ubb.jobs.model.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String id;

    private String username;

    private String password;

    private String role;

    private String firstName;

    private String lastName;

    private String address;

    private String postalCode;

    private String city;

    private String country;

    private String phone;

    private String email;

    private String birthDate;

    private Boolean subscribed;

    private String facebook;

    private String instagram;

    private String twitter;

    private String starAvg;

    private List<AbilityDto> abilities;

    private List<RecommendationDto> recommendations;

    private List<RecommendationDto> recommendationsProvider;

    private List<RecommendationDto> userFor;
//
    private List<RequestDto> requestsMade;
//
    private List<RequestDto> requestsReceived;
//
    private List<ReviewDto> reviewsMade;

    private List<ReviewDto> reviewsReceived;

//    private List<JobDto> jobs;

}
