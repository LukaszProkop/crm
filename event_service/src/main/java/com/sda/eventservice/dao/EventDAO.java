package com.sda.eventservice.dao;


import com.sda.eventservice.model.Comment;
import com.sda.eventservice.model.Event;

import java.util.List;

public interface EventDAO {

    List<Event> getEvents();

    void saveEvent(Event theEvent);

    Event getEvent(Long theId);

    void deleteEvent(Long theId);

    void saveComment(Comment theComment);

}
