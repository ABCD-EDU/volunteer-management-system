package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EventSchedule {

    private int schedId;
    private Timestamp start;
    private Timestamp end;
    private String location;
    private int vol;
    private int eventId;
    private ArrayList<Role> roles;
    private ArrayList<String> participants;

    public EventSchedule(int schedId, Timestamp start, Timestamp end, String location, int vol, int eventId) {
        this.schedId = schedId;
        this.start = start;
        this.end = end;
        this.location = location;
        this.vol = vol;
        this.eventId = eventId;
    }

    public EventSchedule(int schedId, Timestamp start, Timestamp end, String location, int vol, int eventId,
                         ArrayList<Role> roles, ArrayList<String> participants) {
        this.schedId = schedId;
        this.start = start;
        this.end = end;
        this.location = location;
        this.vol = vol;
        this.eventId = eventId;
        this.roles = roles;
        this.participants = participants;
    }

    public int getSchedId() {
        return schedId;
    }

    public void setSchedId(int schedId) {
        this.schedId = schedId;
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

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "schedule " + this.schedId;
    }

}
