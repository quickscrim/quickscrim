package com.quickscrim.quickscrim.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Table
public class Category {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String sport;



    public Category(String sport) {
        this.sport = sport;
    }

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
}
