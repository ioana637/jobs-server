package com.ubb.jobs.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "UserAbilities")
@Data
@IdClass(UserAbilitiesPK.class)
public class UserAbilities {

    @Id
    @Column(name = "ID_USER")
    private Integer idUser;

    @Id
    @Column(name = "ID_ABILITY")
    private Integer idAbility;

    @Column(name = "LEVEL")
    private Level level;

}
