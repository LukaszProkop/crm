package com.sda.eventservice;

import com.sda.eventservice.service.PersonService;
import com.sda.eventservice.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventAggregationService {

    public static void main(String[] args) {
        SpringApplication.run(EventAggregationService.class, args);
    }


}
