package com.quickscrim.repositories;

import com.quickscrim.models.Category;
import com.quickscrim.models.Event;
import com.quickscrim.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.ElementVisitor;
import java.util.List;


@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAllByEventCreator(User user);
    List<Event> findAllByEventSport_Id(Long sport);
}
