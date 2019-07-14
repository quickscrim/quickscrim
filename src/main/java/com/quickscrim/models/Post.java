package com.quickscrim.models;

import org.hibernate.validator.constraints.EAN;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Post {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 8000)
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @OneToOne
    private User postAuthor;

    @OneToOne
    private Category postCategory;

    @OneToMany(mappedBy = "commentOnPost")
    private List<Comment> postComments;



}
