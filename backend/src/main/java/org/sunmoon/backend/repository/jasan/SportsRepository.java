package org.sunmoon.backend.repository.jasan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.jasan.Sports;

public interface SportsRepository extends JpaRepository<Sports, Integer> {
}
