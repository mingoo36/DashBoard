package org.sunmoon.backend.controller.checklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.checklist.GonghakCheck;
import org.sunmoon.backend.repository.checklist.GonghakListRepository;


import java.util.List;

@RestController
public class GonghakListController {

    @Autowired
    GonghakListRepository gonghakListRepository;

    @GetMapping("/api/gonghakcheck")
    public List<GonghakCheck> getGonghaks(){
        return gonghakListRepository.findAll();

    }
}
