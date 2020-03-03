package com.sda.eventservice.controller;

import com.sda.eventservice.dto.PersonDTO;
import com.sda.eventservice.model.Person;
import com.sda.eventservice.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class PersonRegistrationController {



    private final PersonService personService;

    public PersonRegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @ModelAttribute("person")
    public PersonDTO userRegistrationDto() {
        return new PersonDTO();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("person") @Valid PersonDTO personDto,
                                      BindingResult result){

        Person existing = personService.findByEmail(personDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        personService.save(personDto);
        return "redirect:/registration?success";
    }

}

