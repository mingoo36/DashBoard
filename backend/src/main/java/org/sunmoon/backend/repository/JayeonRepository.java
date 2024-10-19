package org.sunmoon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.Jayeon;
import org.sunmoon.backend.entity.Sports;

public interface JayeonRepository extends JpaRepository<Jayeon, Integer> {
}
