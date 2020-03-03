package com.sda.eventservice.service;

import com.sda.eventservice.dto.CommentDTO;
import com.sda.eventservice.dto.EventDTO;
import com.sda.eventservice.dto.EventForApi;
import com.sda.eventservice.dto.PersonForEventDTO;


import java.util.List;

public interface EventService {

    List<EventDTO> getEvents();

    void saveEvent(EventDTO theEvent);

    EventDTO getEvent(Long theId);

    void deleteEvent(Long theId);

    void saveComment(CommentDTO theCommentDTO);

    EventDTO getEventForUpdate(Long theId);

    void saveUpdatedEvent (EventDTO theEventDTO);

    List<PersonForEventDTO> getUsers(Long eventId);

    void deletePersonFromEvent(Long eventId);

    void addPersonToEvent(Long eventId);

    List<EventForApi> getEventsForApiList();

    EventForApi getEventForApi(Long eventId);
}
