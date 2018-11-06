package com.ubb.jobs.model;

import com.ubb.jobs.utils.mapper.LocalDateTimeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name ="Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STARS")
    private Integer stars;

    @Column(name = "ID_USER")
    private Integer idUser;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date;
}
