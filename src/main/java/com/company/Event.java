package com.company;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private List<User> participants;

    public Event(String name, String description, String date, int id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = LocalDateTime.parse(date);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
