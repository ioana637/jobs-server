package com.ubb.jobs.model;

import com.ubb.jobs.utils.mapper.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name="RECOMMENDER")
    private Integer recommender;

    @Column(name="RECOMMENDED_PROVIDER")
    private Integer recommendedProvider;

    @Column(name="USER_FOR")
    private Integer userFor;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date;


}
