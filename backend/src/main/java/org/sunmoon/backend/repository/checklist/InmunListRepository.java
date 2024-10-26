package org.sunmoon.backend.repository.checklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.checklist.InmunCheck;

public interface InmunListRepository extends JpaRepository<InmunCheck, Integer> {
}
