package org.sunmoon.backend.controller.jasan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.jasan.Jayeon;
import org.sunmoon.backend.repository.jasan.JayeonRepository;

import java.util.List;

@RestController
public class JayeonController {

    @Autowired
    JayeonRepository jayeonRepository;

    @GetMapping("/api/jayeon")
    public List<Jayeon> getJayeons(){
        return jayeonRepository.findAll();

    }
}
