package com.ubb.jobs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_FROM")
    private Integer idUserFrom;

    @Column(name = "USER_TO")
    private Integer idUserTo;

    @Column(name = "JOB")
    private Integer idJob;

}
