package org.sunmoon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.Bongwan;
import org.sunmoon.backend.entity.Sports;

public interface BongwanRepository extends JpaRepository<Bongwan, Integer> {
}
