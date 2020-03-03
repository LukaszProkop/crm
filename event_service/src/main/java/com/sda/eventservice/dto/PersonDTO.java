package com.sda.eventservice.dto;


import com.sda.eventservice.model.Event;
import com.sda.eventservice.model.Role;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class PersonDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @Email
    @NotBlank
    private String email;

    @Email
    @NotBlank
    private String confirmEmail;

    private String role;

    private Set<Role> roles;

    private List<Event> eventList;


    @AssertTrue
    private Boolean terms;

    public PersonDTO(Long id, @NotBlank String name, @NotBlank String password, @NotBlank String confirmPassword, @Email @NotBlank String email, @Email @NotBlank String confirmEmail, Set<Role> roles, @AssertTrue Boolean terms) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.roles = roles;
        this.terms = terms;
    }

    public PersonDTO(Long id, Set<Role> roles, List<Event> eventList) {
        this.id = id;
        this.roles = roles;
        this.eventList = eventList;
    }

    public PersonDTO() {

    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public PersonDTO(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}