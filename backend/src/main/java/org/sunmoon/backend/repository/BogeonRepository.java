package org.sunmoon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.Bogeon;
import org.sunmoon.backend.entity.Sports;

public interface BogeonRepository extends JpaRepository<Bogeon, Integer> {
}
