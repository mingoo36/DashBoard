package org.sunmoon.backend.repository;

import org.sunmoon.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Integer> {
}