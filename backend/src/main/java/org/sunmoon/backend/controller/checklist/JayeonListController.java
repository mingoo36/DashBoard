package org.sunmoon.backend.controller.checklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.checklist.JayeonCheck;
import org.sunmoon.backend.repository.checklist.JayeonListRepository;


import java.util.List;

@RestController
public class JayeonListController {

    @Autowired
    JayeonListRepository jayeonListRepository;

    @GetMapping("/api/jayeoncheck")
    public List<JayeonCheck> getJayeons(){
        return jayeonListRepository.findAll();

    }
}
