package ar.com.adriabe.daos;

import ar.com.adriabe.model.Event;

import java.util.List;

/**
 * Created by Mildo on 3/14/15.
 */
public interface EventDao {
    List<Event> findAllPendingEvents();

    Event findById(Long id);

    void save(Event event);
}
