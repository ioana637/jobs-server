package com.ubb.jobs.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FROM")
    private Integer from;

    @Column(name = "TO")
    private Integer to;

    @Column(name = "JOB_ID")
    private Integer jobId;

}
