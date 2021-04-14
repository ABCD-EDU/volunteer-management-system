package models;

import java.util.List;

public class Event {
    private int eventId;
    private String name;
    private String description;
    private List<EventSchedule> schedule;
    private List<Role> roles;

    private Event(int eventId, String name, String desc, List<EventSchedule> sched, List<Role> roles) {
        this.eventId = eventId;
        this.name = name;
        this.description = desc;
        this.schedule = sched;
        this.roles = roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
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
