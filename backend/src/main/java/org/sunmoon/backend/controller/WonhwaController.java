package org.sunmoon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.Wonhwa;
import org.sunmoon.backend.repository.WonhwaRepository;

import java.util.List;

@RestController
public class WonhwaController {

    @Autowired
    WonhwaRepository wonhwaRepository;

    @GetMapping("/api/wonhwa")
    public List<Wonhwa> getWonhwas() {
        return wonhwaRepository.findAll();

    }
}
