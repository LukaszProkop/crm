package com.sda.eventservice.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @NotBlank(message = "Please provide a user name")
    @Size(min = 2, max = 50)
    @Column(name = "name")
    private String name;
    @Email(message = "Please provide a valid Email")
    @Size(min = 3, max = 50)
    @Column(name = "email")
    private String email;
    @Length(min = 5, message = "Your password must have at least 5 characters")
    @NotBlank(message = "Please provide your password")
    @Column(name="password")
    private String password;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "event_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> eventList;


    public Person() {
    }

    public Person(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Person(String name, String email, String password, Set<Role> roles){
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
