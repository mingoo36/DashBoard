package org.sunmoon.backend.repository.checklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.checklist.SportsCheck;

public interface SportsListRepository extends JpaRepository<SportsCheck, Integer> {
}
