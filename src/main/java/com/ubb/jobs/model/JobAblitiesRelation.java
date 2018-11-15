package com.ubb.jobs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Entity
@Data
@NoArgsConstructor
@Table(name = "JobAbilities")
//@IdClass(JobAbilitiesID.class)
//@PrimaryKeyJoinColumns({ @PrimaryKeyJoinColumn(name = "ABILITY_ID"), @PrimaryKeyJoinColumn(name = "JOB_ID")})
public class JobAblitiesRelation implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ABILITY_ID")
//    private Integer abilityId;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "JOB_ID")
//    private Integer jobId;

    @Column(name = "LEVEL")
    private Level level;

    @ManyToOne
    @Id
    @JoinColumn(name = "ABILITY_ID", referencedColumnName = "ID")
    private Ability ability;

    @ManyToOne
    @Id
    @JoinColumn(name = "JOB_ID", referencedColumnName = "ID_JOB")
    private Job job;

}
