package org.sunmoon.backend.controller.checklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.checklist.SportsCheck;
import org.sunmoon.backend.repository.checklist.SportsListRepository;


import java.util.List;

@RestController
public class SportsListController {

    @Autowired
    SportsListRepository sportsListRepository;

    @GetMapping("/api/sportscheck")
    public List<SportsCheck> getSports(){
        return sportsListRepository.findAll();

    }
}
