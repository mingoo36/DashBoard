package org.sunmoon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.Building;
import org.sunmoon.backend.repository.BuildingRepository;


import java.util.List;

@RestController
public class BuildingController {

    @Autowired
    BuildingRepository buildingsRepository;

    @GetMapping("/api/buildings")
    public List<Building> getBuildings() {
        return buildingsRepository.findAll();
    }
}
