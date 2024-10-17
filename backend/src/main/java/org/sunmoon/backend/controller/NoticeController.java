package org.sunmoon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunmoon.backend.entity.Notice;
import org.sunmoon.backend.repository.NoticeRepository;

import java.util.List;

@RestController
public class NoticeController {

    @Autowired
    NoticeRepository noticeRepository;

    @GetMapping("/api/notices")
    public List<Notice> getNotices() {
        return noticeRepository.findAll();
    }
}
