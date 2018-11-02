package com.ubb.jobs.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UserAbilities")
@Data
public class UserAbilities {

    @Column(name = "ID_USER")
    private Integer idUser;

    @Column(name = "ID_ABILITY")
    private Integer idAbility;

    @Column(name = "LEVEL")
    private Level level;

}
