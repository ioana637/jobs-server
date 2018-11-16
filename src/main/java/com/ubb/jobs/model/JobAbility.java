package com.ubb.jobs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "JobAbility")
@IdClass(JobAbilityId.class)
//@PrimaryKeyJoinColumns({ @PrimaryKeyJoinColumn(name = "ABILITY_ID"), @PrimaryKeyJoinColumn(name = "JOB_ID")})
public class JobAbility implements Serializable {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID_JOB_ABILITIES")
//    private Integer id;

    @Column(name = "LEVEL")
    private Level level;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @Id
    @JoinColumn(name = "ID_JOB")
    private Job job;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @Id
    @JoinColumn(name = "ID_ABILITY")
    private Ability ability;



}
