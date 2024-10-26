package org.sunmoon.backend.repository.checklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.checklist.GonghakCheck;

public interface GonghakListRepository extends JpaRepository<GonghakCheck, Integer> {
}
