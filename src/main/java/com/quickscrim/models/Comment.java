package com.quickscrim.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 8000)
    private String body;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreated;

    @OneToOne
    private User commentAuthor;

    @ManyToOne
    private Post comments;


}
