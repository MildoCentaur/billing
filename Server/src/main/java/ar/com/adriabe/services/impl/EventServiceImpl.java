package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.EventDao;
import ar.com.adriabe.model.Event;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mildo on 3/14/15.
 */
@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    EventDao eventDao;

    @Override
    public List<Event> findAllPendingEvents() {
        return eventDao.findAllPendingEvents();
    }

    @Override
    public Event findEventById(Long id) {

        return eventDao.findById(id);
    }

    @Override
    @ILogableOperation(desc = "Registra un nuevo evento", type = ACTION_TYPE.CREATE)
    public void save(Event event) {
        eventDao.save(event);
    }
}
