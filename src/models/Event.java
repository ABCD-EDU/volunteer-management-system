package models;

import java.util.List;
import java.util.Objects;

public class Event {
    private int eventId;
    private String name;
    private String description;
    private List<EventSchedule> schedule;

    public Event(int eventId) {
        this.eventId = eventId;
        this.name = null;
        this.description = null;
        this.schedule = null;
    }

    public Event(int eventId, String name, String desc, List<EventSchedule> sched) {
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

    public void addSchedule(EventSchedule eventSchedule) {
        schedule.add(eventSchedule);
    }

    public void removeSchedule(EventSchedule eventSchedule) {
        schedule.remove(eventSchedule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }
}
