package com.ubb.jobs.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ability")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserAbilities> userAbilities;

    //
    @OneToMany(mappedBy = "ability", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JobAbility> jobs;
//    @ElementCollection
//    @CollectionTable(name = "JobAbility", joinColumns = @JoinColumn(name = "CODE_ABILITY"))
//    @MapKeyJoinColumn(name = "CODE_JOB")
//    Map<Integer, JobAbility> jobAbilities;
//    private List<Integer> jobAbilities;
}
