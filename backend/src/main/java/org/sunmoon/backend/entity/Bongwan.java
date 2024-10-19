package org.sunmoon.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "bongwan_db")
public class Bongwan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "C3", length = 50, nullable = false)
    private String C3;

    @Column(name = "C4", length = 50, nullable = false)
    private String C4;

    @Column(name = "C5", length = 50, nullable = false)
    private String C5;

    @Column(name = "C6", length = 50, nullable = false)
    private String C6;

    @Column(name = "C7", length = 50, nullable = false)
    private String C7;

    @Column(name = "C8", length = 50, nullable = false)
    private String C8;

    @Column(name = "C9", length = 50, nullable = false)
    private String C9;

    @Column(name = "C10", length = 50, nullable = false)
    private String C10;

    @Column(name = "C11", length = 50, nullable = false)
    private String C11;

    @Column(name = "C12", length = 50, nullable = false)
    private String C12;


}

