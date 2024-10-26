package org.sunmoon.backend.controller.checklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.checklist.BongwanCheck;
import org.sunmoon.backend.repository.checklist.BongwanListRepository;


import java.util.List;

@RestController
public class BongwanListController {

    @Autowired
    BongwanListRepository bongwanListRepository;

    @GetMapping("/api/bongwancheck")
    public List<BongwanCheck> getBongwans(){
        return bongwanListRepository.findAll();

    }
}
