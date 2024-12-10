package com.company;

import java.util.ArrayList;
import java.util.List;

public class EventService {
    private final List<Event> events = new ArrayList<>();

    public String getUserEvents(long chatId) {
        String eventsList = "";
        for (Event event : events) {
            if (event.getId() == chatId) {
                eventsList += event.getName() + "\n";
            }
        }
        if (!eventsList.isEmpty()) {
            return eventsList;
        } else {
            return "Sizning tadbirlaringiz yo'q.";
        }
    }

    public String getEventsList() {
        String eventsList = "";
        for (Event event : events) {
            eventsList += event.getName() + " - " + event.getDate() + "\n";
        }
        if (!eventsList.isEmpty()) {
            return eventsList;
        } else {
            return "Tadbirlar mavjud emas.";
        }
    }

    public void addEvent(int id, String name, String description, String date) {
        Event event = new Event(name, description, date, id);
        events.add(event);
    }

    public void deleteEvent(int eventId) {
        events.removeIf(event -> event.getId() == eventId);
    }

}
