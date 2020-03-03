package com.sda.eventservice.service;

import com.google.common.collect.Sets;
import com.sda.eventservice.dao.PersonDAO;
import com.sda.eventservice.dao.RoleDAO;
import com.sda.eventservice.dto.PersonDTO;
import com.sda.eventservice.model.Person;
import com.sda.eventservice.model.Role;
import com.sda.eventservice.model.RoleEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDAO personDAO;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleDAO roleDAO;



    public PersonServiceImpl(PersonDAO personDAO, BCryptPasswordEncoder passwordEncoder, RoleDAO roleDAO) {
        this.personDAO = personDAO;
        this.passwordEncoder = passwordEncoder;
        this.roleDAO = roleDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personDAO.findByEmail(email);
        if (person == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(person.getEmail(),
                person.getPassword(),
                mapRolesToAuthorities(person.getRoles()));
    }

    public Person findByEmail(String email){
        return personDAO.findByEmail(email);
    }

    @Override
    @Transactional
    public void updatePerson(PersonDTO personDTO) {
        Person person;
        person = personDAO.findByEmail(getCurrentPersonLogin());

        String role = personDTO.getRole();

        Role rolePerson = getRole(role);

        person.setRoles(Sets.newHashSet(rolePerson));
        personDAO.save(person);
    }

    private Role getRole(String role) {
        RoleEnum roleEnum = RoleEnum.getRole(role);

        return Optional.ofNullable(roleDAO.findByRole(roleEnum.getRole()))
                .orElseGet(() -> roleDAO.save(new Role(roleEnum.getRole())));
    }

    @Override
    public String getCurrentPersonLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    public Person save(PersonDTO registration){
        Person person = new Person();
        person.setName(registration.getName());
        person.setEmail(registration.getEmail());
        person.setPassword(passwordEncoder.encode(registration.getPassword()));
        if(registration.getRole().equals("ROLE_ADMIN")) {
            person.setRoles(Sets.newHashSet(getRole(registration.getRole())));
        } else {
            String role = "ROLE_USER";
        Role rolePerson = getRole(role);
        person.setRoles(Sets.newHashSet(rolePerson));
        }
        return personDAO.save(person);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonForUpdate(String email) {
        Person person = personDAO.findByEmail(email);
        PersonDTO personDTO = new PersonDTO(person.getId(),
                person.getRoles(),
                person.getEventList());

        return personDTO;
    }
}
