package com.ubb.jobs.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
