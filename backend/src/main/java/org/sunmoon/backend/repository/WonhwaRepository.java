package org.sunmoon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.Sports;
import org.sunmoon.backend.entity.Wonhwa;

public interface WonhwaRepository extends JpaRepository<Wonhwa, Integer> {
}
