package com.quickscrim.models;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Event {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false, length = 8000)
    private String description;

   @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column
    private Date eventDate;

    @Column
    private String locationApi;

    @Column
    private boolean recurring;

    @Column
    private int howOften;

    @ManyToOne
    private User eventCreator;

    @ManyToMany(mappedBy = "userEvents")
    private  List<User> userEvents;

    @OneToOne
    private Category eventSport;


    public Event() { }

    public Event(String eventName, String description) {
        this.eventName = eventName;
        this.description = description;
    }

    public Event(String eventName, String description, Date eventDate, String locationApi, boolean recurring, int howOften, List<User> userEvents, Category eventSport) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.locationApi = locationApi;
        this.recurring = recurring;
        this.howOften = howOften;
        this.userEvents = userEvents;
        this.eventSport = eventSport;
    }

    public Event(String eventName, String description, Date eventDate, String locationApi) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.locationApi = locationApi;
    }

    public Event(String eventName, String description, List<User> userEvents) {
        this.eventName = eventName;
        this.description = description;
        this.userEvents = userEvents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocationApi() {
        return locationApi;
    }

    public void setLocationApi(String locationApi) {
        this.locationApi = locationApi;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public int getHowOften() {
        return howOften;
    }

    public void setHowOften(int howOften) {
        this.howOften = howOften;
    }

    public List<User> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<User> userEvents) {
        this.userEvents = userEvents;
    }

    public User getEventCreator() {
        return eventCreator;
    }

    public void setEventCreator(User eventCreator) {
        this.eventCreator = eventCreator;
    }

    public Category getEventSport() {
        return eventSport;
    }

    public void setEventSport(Category eventSport) {
        this.eventSport = eventSport;
    }
}