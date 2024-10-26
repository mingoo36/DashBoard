package org.sunmoon.backend.controller.checklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.checklist.WonhwaCheck;
import org.sunmoon.backend.repository.checklist.WonhwaListRepository;


import java.util.List;

@RestController
public class WonhwaListController {

    @Autowired
    WonhwaListRepository wonhwaListRepository;

    @GetMapping("/api/wonhwacheck")
    public List<WonhwaCheck> getWonhwas() {
        return wonhwaListRepository.findAll();

    }
}
