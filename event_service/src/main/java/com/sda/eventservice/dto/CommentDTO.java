package com.sda.eventservice.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class CommentDTO {

    private String person;

    @NotBlank(message="is required")
    @Size(max = 500, message="maximum 500 characters required")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    public Long eventId;

    public CommentDTO() {
    }

    public CommentDTO(String person, @Size(max = 500, message = "maximum 500 characters") String content, Date date, Long eventId) {
        this.person = person;
        this.content = content;
        this.date = date;
        this.eventId = eventId;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
