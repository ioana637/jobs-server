package com.ubb.jobs.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAbilitiesPK implements Serializable {
    private Integer idUser;

    private Integer idAbility;
}
