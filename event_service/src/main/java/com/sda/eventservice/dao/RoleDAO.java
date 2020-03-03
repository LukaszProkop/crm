package com.sda.eventservice.dao;

import com.sda.eventservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {

    Role findByRole(String role);

}


