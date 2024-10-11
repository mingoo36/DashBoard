package org.sunmoon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunmoon.backend.entity.Item;
import org.sunmoon.backend.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmailAndPassword(String email, String password);
}