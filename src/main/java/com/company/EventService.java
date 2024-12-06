package com.company;

import java.util.ArrayList;
import java.util.List;

public class EventService {
    private final List<Event> events = new ArrayList<>();


    public String getUserEvents(long chatId) {
        StringBuilder eventsList = new StringBuilder();
        for (Event event : events) {
            if (event.getId() == chatId) {
                eventsList.append(event.getName()).append("\n");
            }
        }
        return eventsList.length() > 0 ? eventsList.toString() : "Sizning tadbirlaringiz yo'q.";
    }


    public String getEventsList() {
        StringBuilder eventsList = new StringBuilder();
        for (Event event : events) {
            eventsList.append(event.getName()).append(" - ").append(event.getDate()).append("\n");
        }
        return eventsList.length() > 0 ? eventsList.toString() : "Tadbirlar mavjud emas.";
    }


    public void addEvent(int Id,String name, String description, String date) {
        Event event = new Event(name, description, date, Id);
        events.add(event);
    }

    // Tadbirni o‘chirish
    public void deleteEvent(int eventId) {
        events.removeIf(event -> event.getId() == eventId);
    }
}
