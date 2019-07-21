package com.quickscrim.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Category {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String sport;
    
    @Column
    private String iconIMG;


    @OneToOne
    private Event sportOFEvent;

    @OneToOne
    private Post postCategory;

    @OneToOne
    private Team sportOfTeam;

    @ManyToMany(mappedBy = "favSports")
    private List<User> favSports;

    public Category(String sport, String iconIMG, Event sportOFEvent, Post postCategory, Team sportOfTeam, List<User> favSports) {
        this.sport = sport;
        this.iconIMG = iconIMG;
        this.sportOFEvent = sportOFEvent;
        this.postCategory = postCategory;
        this.sportOfTeam = sportOfTeam;
        this.favSports = favSports;
    }

    public Category(String sport) {
        this.sport = sport;
    }

    public Category() { }

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

    public Post getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(Post postCategory) {
        this.postCategory = postCategory;
    }

    public Team getSportOfTeam() {
        return sportOfTeam;
    }

    public void setSportOfTeam(Team sportOfTeam) {
        this.sportOfTeam = sportOfTeam;
    }

    public List<User> getFavSports() {
        return favSports;
    }

    public void setFavSports(List<User> favSports) {
        this.favSports = favSports;
    }
}
