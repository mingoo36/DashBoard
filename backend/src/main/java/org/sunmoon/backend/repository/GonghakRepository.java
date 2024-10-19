package org.sunmoon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.Gonghak;
import org.sunmoon.backend.entity.Sports;

public interface GonghakRepository extends JpaRepository<Gonghak, Integer> {
}
