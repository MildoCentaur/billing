package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.EventDao;
import ar.com.adriabe.model.Event;
import ar.com.adriabe.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Mildo on 3/14/15.
 */
@Component("eventDao")
public class EventDaoImpl implements EventDao {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> findAllPendingEvents() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        return eventRepository.findByDateFrom(calendar.getTime());
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.getOne(id);
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }
}
