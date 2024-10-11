package org.sunmoon.backend.entity;

import jakarta.persistence.*;

import lombok.Getter;


@Getter
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    private String img_Path;

    @Column
    private int price;

    @Column
    private int discount_per;


}

