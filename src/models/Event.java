package models;

import java.util.List;

public class Event {
    private int eventId;
    private String name;
    private String description;
    private List<EventSchedule> schedule;

    private Event(int eventId, String name, String desc, List<EventSchedule> sched) {
        this.eventId = eventId;
        this.name = name;
        this.description = desc;
        this.schedule = sched;
    }

    public List<EventSchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<EventSchedule> schedule) {
        this.schedule = schedule;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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
}
