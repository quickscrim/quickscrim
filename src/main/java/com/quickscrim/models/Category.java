package com.quickscrim.models;

import javax.persistence.*;

@Entity
@Table
public class Category {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String sport;
    
    @Column(nullable = false)
    private String iconIMG;


    @OneToOne
    private Event sportOFEvent;

    @OneToOne
    private Post postCategory;

    @OneToOne
    private Team sportOfTeam;

    @ManyToOne
    private User userSports;

    public Category(String sport, String iconIMG, Event sportOFEvent, Post sportOfPost, Team sportOfTeam, User userSports) {
        this.sport = sport;
        this.iconIMG = iconIMG;
        this.sportOFEvent = sportOFEvent;
        this.postCategory = sportOfPost;
        this.sportOfTeam = sportOfTeam;
        this.userSports = userSports;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getIconIMG() {
        return iconIMG;
    }

    public void setIconIMG(String iconIMG) {
        this.iconIMG = iconIMG;
    }

    public Event getSportOFEvent() {
        return sportOFEvent;
    }

    public void setSportOFEvent(Event sportOFEvent) {
        this.sportOFEvent = sportOFEvent;
    }

    public Post getSportOfPost() {
        return postCategory;
    }

    public void setSportOfPost(Post sportOfPost) {
        this.postCategory = sportOfPost;
    }

    public Team getSportOfTeam() {
        return sportOfTeam;
    }

    public void setSportOfTeam(Team sportOfTeam) {
        this.sportOfTeam = sportOfTeam;
    }

    public User getuserSports() {
        return userSports;
    }

    public void setuserSports(User userSports) {
        this.userSports = userSports;
    }
}
