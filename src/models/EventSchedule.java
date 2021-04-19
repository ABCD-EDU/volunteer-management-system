package models;

import java.sql.Timestamp;

public class EventSchedule {
    private Timestamp start, end;
    private String location;
    private int limit;
    private Role role;
    private int schedID;

    public EventSchedule(Timestamp start, Timestamp end, String location, int limit, Role role, int schedID) {
        this.start = start;
        this.end = end;
        this.location = location;
        this.limit = limit;
        this.role = role;
        this.schedID = schedID;
    }

    public int getSchedID() {
        return schedID;
    }

    public void setSchedID(int schedID) {
        this.schedID = schedID;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
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
