package models;

import java.time.LocalDate;

public class EventSchedule {
    private LocalDate start, end;
    private String location;
    private int limit;

    public EventSchedule(LocalDate start, LocalDate end, String location, int limit) {
        this.start = start;
        this.end = end;
        this.location = location;
        this.limit = limit;
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
}
