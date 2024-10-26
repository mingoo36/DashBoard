package org.sunmoon.backend.controller.checklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.checklist.InmunCheck;
import org.sunmoon.backend.repository.checklist.InmunListRepository;


import java.util.List;

@RestController
public class InmunListController {

    @Autowired
    InmunListRepository inmunListRepository;

    @GetMapping("/api/inmuncheck")
    public List<InmunCheck> getInmuns(){
        return inmunListRepository.findAll();

    }
}
