package com.quickscrim.models;

import javax.persistence.*;

@Entity
@Table
public class Team {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;



    public Team(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}