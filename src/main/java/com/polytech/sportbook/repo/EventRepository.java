package com.polytech.sportbook.repo;

import com.polytech.sportbook.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
