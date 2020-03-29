package com.henzel.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mail;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<RSS> rss;
    
    public Long getId() {
        return id;
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<RSS> getRss() {
        return rss;
    }
}