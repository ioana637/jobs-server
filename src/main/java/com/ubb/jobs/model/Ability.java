package com.ubb.jobs.model;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "Abilities")
public class Ability {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DISPLAY")
    private String display;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "ID_ABILITY", table = "UserAbilities")
//    private List<UserAbilities> userAbilities;
//
//    It doesn't create another foreign key
    @ElementCollection
    @CollectionTable(name = "UserAbilities", joinColumns = @JoinColumn(name = "ID_ABILITY"))

    private List<Integer> userAbilities;


    @OneToMany(mappedBy = "ability", targetEntity = JobAblitiesRelation.class)
    private List<Job> jobs;
//    @ElementCollection
//    @CollectionTable(name = "JobAbilities", joinColumns = @JoinColumn(name = "CODE_ABILITY"))
//    @MapKeyJoinColumn(name = "CODE_JOB")
//    Map<Integer, JobAblitiesRelation> jobAbilities;
//    private List<Integer> jobAbilities;
}
