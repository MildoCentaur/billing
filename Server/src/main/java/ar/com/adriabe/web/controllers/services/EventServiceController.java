package ar.com.adriabe.web.controllers.services;


import ar.com.adriabe.model.Event;
import ar.com.adriabe.services.EventService;
import ar.com.adriabe.web.model.WebPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

/**
 * Created by Mildo on 3/13/15.
 */
@Controller
public class EventServiceController extends AbstractServiceController {
    @Autowired
    EventService eventService;


    @RequestMapping(value = "/data/events", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Event> listEvents(Model model) {

        return eventService.findAllPendingEvents();
    }

    @RequestMapping(value = "/data/event/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Event findEvent(Model model, @PathVariable("id") Long id) {
        return eventService.findEventById(id);
    }

    @RequestMapping(value = "/add/event", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse processNewEvent(@ModelAttribute Event event, BindingResult result, SessionStatus status) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Color process initiated");
            eventService.save(event);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("Error de comunicación con el Servidor.");
        }


        return webPageResponse;
    }
}
