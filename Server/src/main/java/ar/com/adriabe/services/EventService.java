package ar.com.adriabe.services;

import ar.com.adriabe.model.Event;

import java.util.List;

/**
 * Created by Mildo on 3/13/15.
 */
public interface EventService {
    List<Event> findAllPendingEvents();

    Event findEventById(Long id);

    void save(Event event);

}
