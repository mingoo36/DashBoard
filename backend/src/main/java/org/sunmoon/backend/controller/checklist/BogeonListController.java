package org.sunmoon.backend.controller.checklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.checklist.BogeonCheck;
import org.sunmoon.backend.repository.checklist.BogeonListRepository;

import java.util.List;

@RestController
public class BogeonListController {

    @Autowired
    BogeonListRepository bogeonListRepository;

    @GetMapping("/api/bogeoncheck")
    public List<BogeonCheck> getBogeons(){
        return bogeonListRepository.findAll();

    }
}
