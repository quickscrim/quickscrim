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

    @OneToMany(mappedBy = "eventByUser")
    private List<Event> userEvents;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_teams",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")}
    )
    private List<Team> userTeams;

    public User(String username, String email, String password, Boolean isAdmin, String firstName, String lastName, String city, String state, String image, String bio, List<Category> favSports, List<Post> userPosts, List<Comment> userComments, List<Event> userEvents, List<Team> userTeams) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        State = state;
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
        return State;
    }

    public void setState(String state) {
        State = state;
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