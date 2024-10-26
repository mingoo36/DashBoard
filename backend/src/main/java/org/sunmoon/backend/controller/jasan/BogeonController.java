package org.sunmoon.backend.controller.jasan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.jasan.Bogeon;
import org.sunmoon.backend.repository.jasan.BogeonRepository;


import java.util.List;

@RestController
public class BogeonController {

    @Autowired
    BogeonRepository bogeonRepository;

    @GetMapping("/api/bogeon")
    public List<Bogeon> getBogeons(){
        return bogeonRepository.findAll();

    }
}
