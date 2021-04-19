package models;

import java.util.Objects;

public class Concern {

    private String type;
    private String desc;
    private int userID;
    private int eventID;

    public enum Type {
        INQUIRY("INQUIRY"),
        LOST_AND_FOUND("LOST_AND_FOUND"),
        SUGGESTION("SUGGESTION"),
        OTHER("OTHER");

        private final String type;

        Type(String type) { this.type = type; }

        public String getType() { return type; }
    }

    public Concern(String type, String desc, int userID, int eventID) {
        this.type = type;
        this.desc = desc;
        this.userID = userID;
        this.eventID = eventID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concern concern = (Concern) o;
        return userID == concern.userID && eventID == concern.eventID && Objects.equals(type, concern.type) && Objects.equals(desc, concern.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, desc, userID, eventID);
    }
}
