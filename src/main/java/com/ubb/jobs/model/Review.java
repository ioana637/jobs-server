package com.ubb.jobs.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name ="Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
}
