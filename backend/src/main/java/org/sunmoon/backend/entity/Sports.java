package org.sunmoon.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "sports_db")
public class Sports {

    @Column(length = 50, nullable = false)
    private String NAME;

    @Id
    @Column(length = 50, nullable = false)
    private String NUMBER;


}

