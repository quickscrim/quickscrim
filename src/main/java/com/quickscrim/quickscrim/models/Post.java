package com.quickscrim.quickscrim.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Post {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String body;


}
