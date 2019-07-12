package com.quickscrim.quickscrim.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = )
    private String username;


    private String email;


    private String password;


    private Boolean isAdmin;


    private String firstname;


    private String lastname;


    private String city;


    private String State;


    private String image;


    private String bio;
}
