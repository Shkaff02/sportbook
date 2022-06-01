package com.polytech.sportbook.repo;

import com.polytech.sportbook.domain.SportObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportObjectRepository extends JpaRepository<SportObject, Long> {
    SportObject findByName(String name);
}
