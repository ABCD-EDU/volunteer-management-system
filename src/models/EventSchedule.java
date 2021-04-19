package models;

import java.time.LocalDate;

public class EventSchedule {
    private LocalDate start, end;
    private String location;
    private int limit;
    private Role role;

    public EventSchedule(LocalDate start, LocalDate end, String location, int limit, Role role) {
        this.start = start;
        this.end = end;
        this.location = location;
        this.limit = limit;
        this.role = role;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }
}
