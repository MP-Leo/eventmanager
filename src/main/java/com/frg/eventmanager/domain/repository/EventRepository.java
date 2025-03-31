package com.frg.eventmanager.domain.repository;

import com.frg.eventmanager.domain.entity.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {

    Optional<Event> findById(UUID id);

    List<Event> findAllActive();

    void save(Event event);

    void delete(Event event);
}
