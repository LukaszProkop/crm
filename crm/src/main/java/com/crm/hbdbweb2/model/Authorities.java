package com.crm.hbdbweb2.model;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "authority")
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
