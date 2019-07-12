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




}