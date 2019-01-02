package com.ubb.jobs.model;

import com.ubb.jobs.utils.mapper.LocalDateConverter;
import com.ubb.jobs.utils.mapper.LocalDateTimeConverter;
import com.ubb.jobs.utils.mapper.LocalTimeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Jobs")
public class Job  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_JOB")
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "JOB")
    private List<Request> requests;

    @Column(name = "ID_CLIENT")
    private Integer idClient;

    @Column(name = "PERIOD_START")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate periodStart = LocalDate.now();

    @Column(name = "PERIOD_END")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate periodEnd;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PEOPLE_REQUIRED")
    private Integer peopleRequired;

    @Column(name = "STATUS")
    private JobStatus status;

    @Column(name = "START_TIME")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime startTime;

    @Column(name = "END_TIME")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime endTime;

    @Column(name = "HOURS_PER_DAY")
    private Integer hoursPerDay;

    @Column(name = "HOURS_PER_WEEK")
    private Integer hoursPerWeek;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Providers_Jobs",
            joinColumns = {@JoinColumn(name= "ID_JOB")},
            inverseJoinColumns = {@JoinColumn(name= "ID_USER")}
    )
    private Set<User> providers;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_JOB")
//    private Set<Review> usersReviewed;

    @Column(name = "LOCATION")
    String location;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name ="JobAbility", joinColumns = @JoinColumn(name = "CODE_JOB"),
//    inverseJoinColumns = @JoinColumn(name = "CODE_ABILITY"))
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job", fetch = FetchType.LAZY)
//    @ElementCollection
//    @CollectionTable(name = "JobAbility", joinColumns = @JoinColumn(name = "CODE_JOB"))
//    @MapKeyJoinColumn(name = "CODE_ABILITY")
//    @Column(name="CODE_ABILITY")
    private List<JobAbility> abilities;

//    Map<Integer, JobAbility> abilities;
//    private List<Integer> abilities;

}
