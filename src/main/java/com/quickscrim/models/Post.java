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
    private User postByUser;

    @OneToOne
    private Category postCategory;

    @OneToMany(mappedBy = "commentOnPost")
    private List<Comment> postComments;

    public Post() {}

    public Post(String title, String body, Date date, User postAuthor, Category postCategory, List<Comment> postComments) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.postByUser = postAuthor;
        this.postCategory = postCategory;
        this.postComments = postComments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getPostAuthor() {
        return postByUser;
    }

    public void setPostAuthor(User postAuthor) {
        this.postByUser = postAuthor;
    }

    public Category getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(Category postCategory) {
        this.postCategory = postCategory;
    }

    public List<Comment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<Comment> postComments) {
        this.postComments = postComments;
    }
}
