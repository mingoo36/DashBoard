package org.sunmoon.backend.repository.checklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.checklist.JayeonCheck;

public interface JayeonListRepository extends JpaRepository<JayeonCheck, Integer> {
}
