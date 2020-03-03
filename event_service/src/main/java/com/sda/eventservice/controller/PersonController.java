package com.sda.eventservice.controller;

import com.sda.eventservice.dto.PersonDTO;
import com.sda.eventservice.model.Event;
import com.sda.eventservice.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;




@Controller
@RequestMapping("/")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("registration")
    public String register() {
        return "registration";
    }

    @RequestMapping("access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @RequestMapping("terms")
    public String terms() {
        return "terms";
    }


    @GetMapping("user/showPersonForm")
    public String showPersonForm(@RequestParam("personEmail") String emial, Model model){
        PersonDTO personDTO = personService.getPersonForUpdate(emial);
        model.addAttribute("person", personDTO);
        return "person-form";
    }

    @PostMapping("user/saveUpdatedPerson")
    public String saveUpdatedPerson(@ModelAttribute("person") PersonDTO personDTO){
        personService.updatePerson(personDTO);

        return "redirect:/logout";
    }

}
