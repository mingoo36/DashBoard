package org.sunmoon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.Inmun;
import org.sunmoon.backend.repository.InmunRepository;

import java.util.List;

@RestController
public class InmunController {

    @Autowired
    InmunRepository inmunRepository;

    @GetMapping("/api/inmun")
    public List<Inmun> getInmuns(){
        return inmunRepository.findAll();

    }
}
