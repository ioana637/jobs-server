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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_ABILITY")
    private List<UserAbilities> userAbilities;

    @ElementCollection
    @CollectionTable(name = "JobAbilities", joinColumns = @JoinColumn(name = "ID"))
    @Column(name = "ABILITY_CODE")
    private List<Long> abilities;
}
