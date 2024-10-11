package org.sunmoon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.Sports;

public interface SportsRepository extends JpaRepository<Sports, Integer> {
}
