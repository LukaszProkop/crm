package com.sda.eventservice.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message="is required")
    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @NotBlank(message="is required")
    @Size(min=20, message="minimum 20 characters required")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull(message="is required")
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "start_date")
    private Date startDate;

    @NotNull(message="is required")
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    @JoinColumn(name = "event_id")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "event_person",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> personList;

    public Event(String title, String description, Date startDate, Date endDate, Long id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event() {
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void addComment(Comment tempComment) {
        if (comments == null) {
            comments = new ArrayList<Comment>();
        }
        comments.add(tempComment);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
