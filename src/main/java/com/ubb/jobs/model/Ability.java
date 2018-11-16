package com.ubb.jobs.model;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "Abilities")
public class Ability  {

    @Id
    @Column(name = "ID_ABILITY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DISPLAY")
    private String display;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "ability")
    private List<UserAbilities> userAbilities;

    //
    @OneToMany(mappedBy = "ability", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JobAbility> jobs;
//    @ElementCollection
//    @CollectionTable(name = "JobAbility", joinColumns = @JoinColumn(name = "CODE_ABILITY"))
//    @MapKeyJoinColumn(name = "CODE_JOB")
//    Map<Integer, JobAbility> jobAbilities;
//    private List<Integer> jobAbilities;
}
