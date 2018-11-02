package com.ubb.jobs.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_ID")
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "JOB_ID")
    private List<Request> requestList;

    @Column(name = "CLIENT_ID")
    private Integer clientId;

    @Column(name = "PERIOD_START")
    private Date periodStart;

    @Column(name = "PERIOD_END")
    private Date periodEnd;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PEOPLE_REQUIRED")
    private Integer peopleRequired;

    @Column(name = "AVAILABLE")
    private Boolean available;

    @Column(name = "START_TIME")
    private LocalTime startTime;

    @Column(name = "END_TIME")
    private LocalTime endTime;

    @Column(name = "HOURS_PER_DAY")
    private Integer hoursPerDay;

    @Column(name = "HOURS_PER_WEEK")
    private Integer hoursPerWeek;

    @ElementCollection
    @CollectionTable(name = "ProvidersJobs", joinColumns = @JoinColumn(name = "ID_JOB"))
    @Column(name = "ID_PROVIDER")
    private List<Long> providers;

    @ElementCollection
    @CollectionTable(name = "JobAbilities", joinColumns = @JoinColumn(name = "ID_JOB"))
    @Column(name = "JOB_CODE")
    private List<Long> abilities;

}
