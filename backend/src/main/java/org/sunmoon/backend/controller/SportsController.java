package org.sunmoon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.Sports;
import org.sunmoon.backend.repository.SportsRepository;

import java.util.List;

@RestController
public class SportsController {

    @Autowired
    SportsRepository sportsRepository;

    @GetMapping("/api/sports")
    public List<Sports> getSports(){
        return sportsRepository.findAll();

    }
}
