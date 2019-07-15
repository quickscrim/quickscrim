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
    private User commentByUser;

    @ManyToOne
    private Post commentOnPost;


    public Comment(String body, Date dateCreated, User commentAuthor, Post comments) {
        this.body = body;
        this.dateCreated = dateCreated;
        this.commentByUser = commentAuthor;
        this.commentOnPost = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getCommentAuthor() {
        return commentByUser;
    }

    public void setCommentAuthor(User commentAuthor) {
        this.commentByUser = commentAuthor;
    }

    public Post getComments() {
        return commentOnPost;
    }

    public void setComments(Post comments) {
        this.commentOnPost = comments;
    }
}
