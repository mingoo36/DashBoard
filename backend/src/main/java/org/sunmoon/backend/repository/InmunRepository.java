package org.sunmoon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.Inmun;
import org.sunmoon.backend.entity.Sports;

public interface InmunRepository extends JpaRepository<Inmun, Integer> {
}
