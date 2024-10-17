package org.sunmoon.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "notices")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "time", length = 50, nullable = false)
    private String time;

}

