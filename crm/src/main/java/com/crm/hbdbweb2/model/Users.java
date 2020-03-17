package com.crm.hbdbweb2.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.sql.Update;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password", columnDefinition = "char(68)")
    private String password;

    @Column(name = "enabled", columnDefinition = "tinyint(1)")
    private int enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "users",
            cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Authorities> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<Authorities> getRoles() {
        return roles;
    }

    public void setRoles(List<Authorities> roles) {
        this.roles = roles;
    }

    public void addRole(String role) {
        if(roles == null) {
            roles = new ArrayList<>();
        }

        this.roles.add(new Authorities(role, this));
    }
}
