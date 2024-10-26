package org.sunmoon.backend.repository.checklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.checklist.WonhwaCheck;

public interface WonhwaListRepository extends JpaRepository<WonhwaCheck, Integer> {
}
