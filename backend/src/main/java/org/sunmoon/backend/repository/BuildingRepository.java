package org.sunmoon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}
