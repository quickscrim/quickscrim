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

    @ManyToOne
    private User teamByUser;


    public Team(String name, User usersTeams) {
        this.name = name;
        this.teamByUser = usersTeams;
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

    public User getUsersTeams() {
        return teamByUser;
    }

    public void setUsersTeams(User usersTeams) {
        this.teamByUser = usersTeams;
    }
}