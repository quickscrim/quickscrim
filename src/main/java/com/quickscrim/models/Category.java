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
    private Post sportOfPost;

    @OneToOne
    private Team sportOfTeam;

    @ManyToOne
    private User userFavSports;

}
