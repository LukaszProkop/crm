package com.crm.hbdbweb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class Hbdbweb2Application {

    public static void main(String[] args) {
        SpringApplication.run(Hbdbweb2Application.class, args);
    }

    @RequestMapping("/")
    public String astroGuy() {
        return "index";
    }

}
