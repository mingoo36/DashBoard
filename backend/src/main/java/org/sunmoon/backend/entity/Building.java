package org.sunmoon.backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;


@Getter
@Entity
@Table(name = "BuildingInfo")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 새로운 기본 키 필드

    @Column(name = "building_name", length = 50, nullable = false)
    private String buildingName;

    @Column(name = "manager", length = 50, nullable = false)
    private String manager;

    @Column(name = "location", length = 50, nullable = false)
    private String location;

    @Column(name = "number", length = 50, nullable = false, unique = true)
    private String number; // 고유 제약 조건 추가

    @Column(name = "C5", length = 50)
    private String C5;

    @Column(name = "C6", length = 50)
    private String C6;
}

