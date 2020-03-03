package com.sda.eventservice.service;

import com.sda.eventservice.dto.PersonDTO;
import com.sda.eventservice.model.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface PersonService extends UserDetailsService {

    String getCurrentPersonLogin();

    Person save(PersonDTO registration);

    Person findByEmail(String email);

    void updatePerson(PersonDTO personDTO);

    PersonDTO getPersonForUpdate(String email);



}
