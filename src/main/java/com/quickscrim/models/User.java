package com.quickscrim.models;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50, unique = true)
    @NotBlank(message = "User must enter username.")
    @Size(min = 4, message = "Username must be at least 4 characters.")
    private String username;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Must enter email.")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Must enter password to register.")
    @Size(min = 8, message = "Your password must be at least 8 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.\n")
    private String password;

    @Column
    private Boolean isAdmin;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String image;

    @Column
    private String bio;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_category",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> favSports;

    @OneToMany(mappedBy = "postByUser")
    private List<Post> userPosts;

    @OneToMany(mappedBy = "commentByUser")
    private List<Comment> userComments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_events",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private List<Event> userEvents;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_teams",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")}
    )
    private List<Team> userTeams;


    public User() {
    }

    public User(User copy) {
        this.id = copy.id;
        this.username = copy.username;
        this.email = copy.email;
        this.password = copy.password;
        this.isAdmin = copy.isAdmin;
        this.firstName = copy.firstName;
        this.lastName = copy.lastName;
        this.city = copy.city;
        this.state = copy.state;
        this.image = copy.image;
        this.bio = copy.bio;
        this.favSports = copy.favSports;
        this.userPosts = copy.userPosts;
        this.userComments = copy.userComments;
        this.userEvents = copy.userEvents;
        this.userTeams = copy.userTeams;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(long id, String username, String email, String password, Boolean isAdmin, String firstName, String lastName, String city, String state, String image, String bio, List<Category> favSports, List<Post> userPosts, List<Comment> userComments, List<Event> userEvents, List<Team> userTeams) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.image = image;
        this.bio = bio;
        this.favSports = favSports;
        this.userPosts = userPosts;
        this.userComments = userComments;
        this.userEvents = userEvents;
        this.userTeams = userTeams;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Category> getFavSports() {
        return favSports;
    }

    public void setFavSports(List<Category> favSports) {
        this.favSports = favSports;
    }

    public List<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public List<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }

    public List<Event> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<Event> userEvents) {
        this.userEvents = userEvents;
    }

    public List<Team> getUserTeams() {
        return userTeams;
    }

    public void setUserTeams(List<Team> userTeams) {
        this.userTeams = userTeams;
    }
}