package com.ubb.jobs.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "UserAbilities")
@Data
@IdClass(UserAbilitiesPK.class)
public class UserAbilities {

    @Column(name = "LEVEL")
    private Level level;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @Id
    @JoinColumn(name = "ID_USER")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @Id
    @JoinColumn(name = "ID_ABILITY")
    private Ability ability;

}
