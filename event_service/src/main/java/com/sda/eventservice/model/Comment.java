package com.sda.eventservice.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "person")
    private String person;

    @NotBlank(message="is required")
    @Size(max = 500, message="maximum 500 characters required")
    @Column(name = "content")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "date")
    private Date date;

    @Column(name = "event_id")
    public Long eventId;

    public Comment(String person, String content, Date date) {
        this.person = person;
        this.content = content;
        this.date = date;
    }

    public Comment() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", person='" + person + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
