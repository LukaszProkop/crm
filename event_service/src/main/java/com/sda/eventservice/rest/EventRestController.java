package com.sda.eventservice.rest;

import com.sda.eventservice.dto.EventForApi;
import com.sda.eventservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<EventForApi> getEvents() {
        List<EventForApi> eventForApiList = eventService.getEventsForApiList();
        return eventForApiList;
    }

    @GetMapping("events/{eventId}")
    public EventForApi getEventForApi(@PathVariable Long eventId) {
        EventForApi eventForApi = eventService.getEventForApi(eventId);
        if(eventForApi == null) {
            throw new EventNotFoundException("Event id not found - " + eventId);
        }
        return eventForApi;
    }
}
