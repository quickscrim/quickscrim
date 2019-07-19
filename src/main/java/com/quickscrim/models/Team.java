package com.quickscrim.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Team {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "userTeams")
    private List<User> userTeams;

    public Team(String name, List<User> userTeams) {
        this.name = name;
        this.userTeams = userTeams;
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

    public List<User> getUserTeams() {
        return userTeams;
    }

    public void setUserTeams(List<User> userTeams) {
        this.userTeams = userTeams;
    }
}