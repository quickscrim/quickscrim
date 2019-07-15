package com.quickscrim.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isAdmin;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String State;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String bio;

    @OneToMany(mappedBy = "userSports")
    private List<Category> favSports;

    @OneToMany(mappedBy = "postByUser")
    private List<Post> userPosts;

    @OneToMany(mappedBy = "commentByUser")
    private List<Comment> userComments;

    @OneToMany(mappedBy = "eventByUser")
    private List<Event> userEvents;

    @OneToMany(mappedBy = "teamByUser")
    private List<Team> userTeams;




}