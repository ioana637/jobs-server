package com.ubb.jobs.model;

import com.ubb.jobs.utils.mapper.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "POSTAL_CODE")
    private Integer postalCode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BIRTH_DATE")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate birthDate;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "SUBSCRIBED")
    private Boolean subscribed;

    @Column(name = "FACEBOOK")
    private String facebook;

    @Column(name = "INSTAGRAM")
    private String instagram;

    @Column(name = "TWITTER")
    private String twitter;

    @Column(name = "STAR_AVG")
    private Float starAvg;

    @Column(name = "ROLE")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "RECOMMENDER")
    private List<Recommendation> recommendations;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "RECOMMENDED_PROVIDER")
    private List<Recommendation> recommendationsProvider;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "USER_FROM")
    private List<Request> requestsMade;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "USER_TO")
    private List<Request> requestsReceived;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<UserAbilities> userAbilities;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_CLIENT")
    private List<Job> jobs;

    @ElementCollection
    @CollectionTable(name = "ProvidersJobs", joinColumns = @JoinColumn(name = "ID_JOB"))
    @Column(name = "ID")
    private List<Integer> requestedJobs;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_USER")
    private List<Review> reviewsMade;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_USER_FOR")
    private List<Review> reviewsReceived;

}
