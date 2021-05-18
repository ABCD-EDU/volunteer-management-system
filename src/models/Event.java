package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event {

    private int event_id;
    private String name;
    private String description;
    private ArrayList<EventSchedule> schedules;

    public Event(int event_id, String name, String description) {
        this.event_id = event_id;
        this.name = name;
        this.description = description;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
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

    public ArrayList<EventSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<EventSchedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event_id=" + event_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", schedules=" + schedules +
                '}';
    }
}
