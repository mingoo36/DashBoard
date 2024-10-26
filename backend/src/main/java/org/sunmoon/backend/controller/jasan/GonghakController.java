package org.sunmoon.backend.controller.jasan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.jasan.Gonghak;
import org.sunmoon.backend.repository.jasan.GonghakRepository;

import java.util.List;

@RestController
public class GonghakController {

    @Autowired
    GonghakRepository gonghakRepository;

    @GetMapping("/api/gonghak")
    public List<Gonghak> getGonghaks(){
        return gonghakRepository.findAll();

    }
}