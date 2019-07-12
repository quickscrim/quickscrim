package com.quickscrim.models;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date eventDate;

    @Column(nullable = false)
    private int locationApi;

    @Column(nullable = false)
    private boolean recurring;

    @Column(nullable = false)
    private int howOften;

    @OneToOne
    private User eventCreator;

}