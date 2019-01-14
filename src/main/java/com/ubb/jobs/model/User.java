package com.ubb.jobs.model;

import com.ubb.jobs.utils.mapper.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "RECOMMENDER")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Recommendation> recommendations;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "RECOMMENDED_PROVIDER")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Recommendation> recommendationsProvider;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "USER_FOR")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Recommendation> userFor;

//    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "USER_FROM")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Request> requestsMade;

    @OneToMany(cascade = CascadeType.REMOVE)
//    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_TO")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Request> requestsReceived;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserAbilities> userAbilities;

//    @OneToMany(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "ID_CLIENT")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Job> jobs;

//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(
//            name = "Providers_Jobs",
//            joinColumns = {@JoinColumn(name= "ID_USER")},
//            inverseJoinColumns = {@JoinColumn(name= "ID_JOB")}
//    )
//    private Set<Job> jobsWorking;


//    @ElementCollection
//    @CollectionTable(name = "ProvidersJobs", joinColumns = @JoinColumn(name = "ID_JOB"))
////    @Column(name = "ID")
//    private List<Integer> requestedJobs;


    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_USER")
    private List<Review> reviewsMade;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_USER_FOR")
    private List<Review> reviewsReceived;

}
