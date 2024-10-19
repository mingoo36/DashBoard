package org.sunmoon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.Bongwan;
import org.sunmoon.backend.repository.BongwanRepository;

import java.util.List;

@RestController
public class BongwanController {

    @Autowired
    BongwanRepository bongwanRepository;

    @GetMapping("/api/bongwan")
    public List<Bongwan> getBongwans(){
        return bongwanRepository.findAll();

    }
}
