package org.sunmoon.backend.repository.checklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.checklist.BogeonCheck;

public interface BogeonListRepository extends JpaRepository<BogeonCheck, Integer> {
}
