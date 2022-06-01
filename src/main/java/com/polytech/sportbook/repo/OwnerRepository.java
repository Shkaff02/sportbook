package com.polytech.sportbook.repo;

import com.polytech.sportbook.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
