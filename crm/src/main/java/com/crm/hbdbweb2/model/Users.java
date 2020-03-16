package com.crm.hbdbweb2.model;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    @JoinColumn(name = "username")
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
}
