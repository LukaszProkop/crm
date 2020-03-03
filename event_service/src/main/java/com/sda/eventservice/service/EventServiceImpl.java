package com.sda.eventservice.service;

import com.sda.eventservice.dao.EventDAO;
import com.sda.eventservice.dao.PersonDAO;
import com.sda.eventservice.dto.CommentDTO;
import com.sda.eventservice.dto.EventDTO;
import com.sda.eventservice.dto.EventForApi;
import com.sda.eventservice.dto.PersonForEventDTO;
import com.sda.eventservice.model.Comment;
import com.sda.eventservice.model.Event;
import com.sda.eventservice.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventDAO eventDAO;
    private final PersonDAO personDAO;
    private final PersonService personService;

    public EventServiceImpl(EventDAO eventDAO, PersonDAO personDAO, PersonService personService) {
        this.eventDAO = eventDAO;
        this.personDAO = personDAO;
        this.personService = personService;
    }

    @Override
    @Transactional
    public List<EventDTO> getEvents() {
        List<Event> events = eventDAO.getEvents();
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event tempevent : events) {
            EventDTO eventDTO = new EventDTO(tempevent.getId(),
                    tempevent.getTitle(),
                    tempevent.getDescription(),
                    tempevent.getStartDate(),
                    tempevent.getEndDate(),
                    tempevent.getComments());
            eventDTOs.add(eventDTO);
        }
        return eventDTOs;
    }

    @Override
    @Transactional
    public void saveEvent(EventDTO theEventDTO) {
        Event event = new Event();
        event.setDescription(theEventDTO.getDescription());
        event.setTitle(theEventDTO.getTitle());
        event.setStartDate(theEventDTO.getStartDate());
        event.setEndDate(theEventDTO.getEndDate());
        eventDAO.saveEvent(event);
    }

    @Override
    @Transactional
    public void saveUpdatedEvent(EventDTO theEventDTO) {
        Event event;
        event = eventDAO.getEvent(theEventDTO.getId());
        event.setDescription(theEventDTO.getDescription());
        event.setTitle(theEventDTO.getTitle());
        event.setStartDate(theEventDTO.getStartDate());
        event.setEndDate(theEventDTO.getEndDate());

        eventDAO.saveEvent(event);
    }

    @Override
    public List<PersonForEventDTO> getUsers(Long eventId) {
        Event event = eventDAO.getEvent(eventId);
        List<PersonForEventDTO> personForEventDTOs = new ArrayList<>();
        for (Person person : event.getPersonList()) {
            personForEventDTOs.add(new PersonForEventDTO(person.getName(), person.getEmail()));
        }
        return personForEventDTOs;
    }

    @Override
    public EventDTO getEvent(Long theId) {

        Event event = eventDAO.getEvent(theId);
        EventDTO eventDTO = new EventDTO(event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate(),
                event.getComments());

        return eventDTO;
    }

    @Override
    @Transactional
    public void deleteEvent(Long theId) {
        eventDAO.deleteEvent(theId);
    }

    @Override
    @Transactional
    public void saveComment(CommentDTO theCommentDTO) {
        Comment theComment = new Comment();
        theComment.setEventId(theCommentDTO.getEventId());
        theComment.setPerson(theCommentDTO.getPerson());
        theComment.setDate(theCommentDTO.getDate());
        theComment.setContent(theCommentDTO.getContent());
        eventDAO.saveComment(theComment);
    }

    @Override
    public EventDTO getEventForUpdate(Long theId) {
        Event event = eventDAO.getEvent(theId);
        EventDTO eventDTO = new EventDTO(event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate());

        return eventDTO;
    }

    @Override
    @Transactional
    public void deletePersonFromEvent(Long eventId) {

        Person person = personDAO.findByEmail(personService.getCurrentPersonLogin());
        Event event = eventDAO.getEvent(eventId);
        event.getPersonList().remove(person);
        eventDAO.saveEvent(event);
    }

    @Override
    @Transactional
    public void addPersonToEvent(Long eventId) {
        Person person = personDAO.findByEmail(personService.getCurrentPersonLogin());
        Event event = eventDAO.getEvent(eventId);
        event.getPersonList().add(person);
        eventDAO.saveEvent(event);
    }

    @Override
    public List<EventForApi> getEventsForApiList() {
        List<Event> events = eventDAO.getEvents();
        List<EventForApi> eventForApiList = new ArrayList<>();
        for (Event tempevent : events) {
           eventForApiList.add(new EventForApi(tempevent.getId(), tempevent.getTitle(),
                   tempevent.getDescription(), tempevent.getStartDate(), tempevent.getEndDate()));
        }
        return eventForApiList;
    }

    @Override
    public EventForApi getEventForApi(Long eventId) {

        Event event = eventDAO.getEvent(eventId);
        EventForApi eventForApi = new EventForApi(event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate());

        return eventForApi;
    }

}
