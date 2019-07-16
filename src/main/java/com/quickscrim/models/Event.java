package com.quickscrim.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
    @Column(nullable = false)
    private Date eventDate;

    @Column(nullable = false)
    private int locationApi;

    @Column(nullable = false)
    private boolean recurring;

    @Column(nullable = false)
    private int howOften;

    @OneToOne
    private User eventByUser;


    public Event(String eventName, String description, Date eventDate, int locationApi, boolean recurring, int howOften, User eventCreator) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.locationApi = locationApi;
        this.recurring = recurring;
        this.howOften = howOften;
        this.eventByUser = eventCreator;
    }

    public Event() { }

    public Event(String eventName, String description, Date eventDate, int locationApi) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.locationApi = locationApi;
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

    public int getLocationApi() {
        return locationApi;
    }

    public void setLocationApi(int locationApi) {
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

    public User getEventCreator() {
        return eventByUser;
    }

    public void setEventCreator(User eventCreator) {
        this.eventByUser = eventCreator;
    }
}