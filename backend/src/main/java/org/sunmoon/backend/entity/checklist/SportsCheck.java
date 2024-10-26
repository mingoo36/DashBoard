package org.sunmoon.backend.entity.checklist;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "sportsList")
public class SportsCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "C2", length = 50, nullable = false)
    private String C2;

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

}

