package com.sda.eventservice.dto;

import com.sda.eventservice.model.Comment;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class EventDTO {

    private Long id;

    @NotBlank(message="is required")
    private String title;

    @NotBlank(message="is required")
    @Size(min=20, message="minimum 20 characters required")
    private String description;

    @NotNull(message="is required")
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startDate;

    @NotNull(message="is required")
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endDate;

    private List<Comment> comments;

    public EventDTO() {
    }

    public EventDTO(Long id, String title, String description, Date startDate, Date endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EventDTO(Long id, String title, String description, Date startDate, Date endDate, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.comments = comments;
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
}
