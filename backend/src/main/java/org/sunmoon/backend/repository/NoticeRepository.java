package org.sunmoon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
