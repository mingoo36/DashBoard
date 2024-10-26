package org.sunmoon.backend.controller.jasan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.jasan.Wonhwa;
import org.sunmoon.backend.repository.jasan.WonhwaRepository;

import java.util.List;

@RestController
public class WonhwaController {

    @Autowired
    WonhwaRepository wonhwaRepository;

    @GetMapping("/api/wonhwa")
    public List<Wonhwa> getWonhwas() {
        return wonhwaRepository.findAll();

    }
}
