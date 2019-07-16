package com.quickscrim.repositories;

import com.quickscrim.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

}
