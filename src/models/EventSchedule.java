package models;

import java.sql.Timestamp;
import java.util.List;

public class EventSchedule {
    private Timestamp start, end;
    private String location;
    private int limit;
    private int schedID;

    public EventSchedule(Timestamp start, Timestamp end, String location, int limit, int schedID) {
        this.start = start;
        this.end = end;
        this.location = location;
        this.limit = limit;
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

}
