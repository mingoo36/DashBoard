package org.sunmoon.backend.controller.jasan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.jasan.Bongwan;
import org.sunmoon.backend.repository.jasan.BongwanRepository;

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
