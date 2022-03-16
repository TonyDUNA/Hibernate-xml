package ru.glebov.entity;

import java.util.Date;

public class Event {
    private long id;
    private String title;
    private Date date;

    public Event() {}

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
