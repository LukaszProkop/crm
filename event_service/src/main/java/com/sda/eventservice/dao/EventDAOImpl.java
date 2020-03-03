package com.sda.eventservice.dao;

import com.sda.eventservice.model.Comment;
import com.sda.eventservice.model.Event;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EventDAOImpl implements EventDAO {

    private final EntityManager entityManager;

    public EventDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Event> getEvents() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Event> theQuery =
                currentSession.createQuery("from Event order by startDate", Event.class);
        List<Event> events = theQuery.getResultList();
        return events;
    }

    @Override
    public void saveEvent(Event theEvent) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theEvent);
    }

    @Override
    public Event getEvent(Long theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Event theEvent =
                currentSession.get(Event.class, theId);
        return theEvent;
    }

    @Override
    public void deleteEvent(Long theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("delete from Event where id=:eventId");
        theQuery.setParameter("eventId", theId);
        theQuery.executeUpdate();

    }

    @Override
    public void saveComment(Comment theComment) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theComment);
    }
}
