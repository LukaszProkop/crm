package com.sda.eventservice.controller;


import com.sda.eventservice.dto.*;
import com.sda.eventservice.dto.CommentDTO;
import com.sda.eventservice.dto.EventDTO;
import com.sda.eventservice.dto.SearchDTO;
import com.sda.eventservice.model.Person;
import com.sda.eventservice.service.EventService;

import com.sda.eventservice.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class EventController {


    private final EventService eventService;
    private final PersonService personService;

    public EventController(EventService eventService, PersonService personService) {
        this.eventService = eventService;
        this.personService = personService;
    }

    @GetMapping("")
    public String mainPage(Model theModel) {
        SearchDTO search = new SearchDTO();
        search.setSearchString("find event by typing here whole or part of a title");
        List<EventDTO> eventDTOs = eventService.getEvents();
        Person personDTO = personService.findByEmail(personService.getCurrentPersonLogin());
        theModel.addAttribute("events", eventDTOs);
        theModel.addAttribute("search", search);
        theModel.addAttribute("person", personDTO);
        return "main-page";
    }



    @GetMapping("showDetails")
    public String showDetails(@RequestParam("eventId") Long eventId, Model theModel, HttpServletRequest request) {
        List<PersonForEventDTO> personForEventDTOs = eventService.getUsers(eventId);
        EventDTO eventDTO = eventService.getEvent(eventId);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setEventId(eventDTO.getId());
        ParticipleListDTO participleListDTO = new ParticipleListDTO();
        participleListDTO.setEventId(eventDTO.getId());
        participleListDTO.setStatus("not in");

        for (PersonForEventDTO personForEventDTO : personForEventDTOs) {
            if(personService.getCurrentPersonLogin().equals("adam")){
                participleListDTO.setStatus("adam");
                break;
            } else if (personService.getCurrentPersonLogin().equals("anonymousUser")) {
                participleListDTO.setStatus(null);
                break;
            } else if (personService.getCurrentPersonLogin().equals(personForEventDTO.getEmail())) {
                participleListDTO.setStatus("in");
                break;
            }
        }
        System.out.println(participleListDTO.getStatus());
        theModel.addAttribute("event", eventDTO);
        if(!theModel.containsAttribute("comment")) {
            theModel.addAttribute("comment", commentDTO);
        }
        theModel.addAttribute("users", personForEventDTOs);
        theModel.addAttribute("listStatus", participleListDTO);
        return "event-details";
    }

    @GetMapping("organizer/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        EventDTO eventDTO = new EventDTO();
        theModel.addAttribute("event", eventDTO);
        return "event-form";
    }

    @PostMapping("organizer/saveEvent")
    public String saveEvent(@Valid @ModelAttribute("event") EventDTO theEvent, BindingResult result) {
        if(result.hasErrors()) {
            return "event-form";
        }
        eventService.saveEvent(theEvent);
        return "redirect:/";
    }

    @PostMapping("organizer/saveUpdatedEvent")
    public String saveUpdatedEvent(@Valid @ModelAttribute("event") EventDTO eventDTO, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("event", eventDTO);
            return "event-form-update";
        }
        eventService.saveUpdatedEvent(eventDTO);
        return "redirect:/";
    }

    @PostMapping("user/saveComment")
    public String saveComment(@Valid @ModelAttribute("comment") CommentDTO commentDTO, BindingResult result, Model model,
                              HttpServletRequest request) {
        if(result.hasErrors()) {
            return showDetails(commentDTO.getEventId(), model, request);
        }
        if ("anonymousUser".equals(personService.getCurrentPersonLogin())) {
            return "access-denied";
        }
        Person person;
        person = personService.findByEmail(personService.getCurrentPersonLogin());
        commentDTO.setPerson(person.getName());
        commentDTO.setDate(new Date());
        eventService.saveComment(commentDTO);
        return showDetails(commentDTO.getEventId(), model, request);
    }

    @GetMapping("organizer/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("eventId") Long theId, Model model) {
        EventDTO eventDTO = eventService.getEventForUpdate(theId);
        model.addAttribute("event", eventDTO);
        return "event-form-update";
    }

    @GetMapping("admin/delete")
    public String deleteEvent(@RequestParam("eventId") Long theId) {
        eventService.deleteEvent(theId);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("search") SearchDTO searchDTO, Model model) {
        List<EventDTO> eventDTOs = eventService.getEvents();
        List<EventDTO> eventsRes = new ArrayList<>();
        for (EventDTO eventDTO : eventDTOs) {
            if (eventDTO.getTitle().contains(searchDTO.getSearchString())) {
                eventsRes.add(eventDTO);
            }
        }
        model.addAttribute("search", searchDTO);
        model.addAttribute("events", eventsRes);
        return "search";
    }
    @PostMapping("/addUserToList")
    public String addUserToList (@ModelAttribute("status") ParticipleListDTO participleList, Model model, HttpServletRequest request) {
        System.out.println(participleList.getStatus());
        if(participleList.getStatus().equals("in")) {
            eventService.deletePersonFromEvent(participleList.getEventId());
        }else if (participleList.getStatus().equals("not in")) {
            eventService.addPersonToEvent(participleList.getEventId());
        }
        return showDetails(participleList.getEventId(), model, request);
    }
}
