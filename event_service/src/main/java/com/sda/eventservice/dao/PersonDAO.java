package com.sda.eventservice.dao;

import com.sda.eventservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {

    Person findByEmail(String email);
}
