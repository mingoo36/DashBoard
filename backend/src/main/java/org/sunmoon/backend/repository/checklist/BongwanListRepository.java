package org.sunmoon.backend.repository.checklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.checklist.BongwanCheck;

public interface BongwanListRepository extends JpaRepository<BongwanCheck, Integer> {
}
