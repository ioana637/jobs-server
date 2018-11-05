package com.ubb.jobs.model;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

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
//    @ElementCollection
//    @CollectionTable(name = "UserAbilities", joinColumns = @JoinColumn(name = "ID_ABILITY"))
//    @Column(name = "ID")
//    private List<Integer> userAbilities;


    @ElementCollection
    @CollectionTable(name = "JobAbilities", joinColumns = @JoinColumn(name = "CODE_ABILITY"))
    @Column(name = "ID")
    private List<Integer> jobAbilities;
}
