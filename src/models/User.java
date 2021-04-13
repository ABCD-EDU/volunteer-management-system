package models;

// USER_ACC(user_id, username, password, type)
public class User {
    private int id;
    private String username;
    private Type type;

    public enum Type {
        VOLUNTEER("VOLUNTEER"),
        ADMINISTRATOR("ADMINISTRATOR");

        private final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public User(int id, String username, Type type) {
        this.id = id;
        this.username = username;
        this.type = type;
    }

    public User(User user) {
        this.id = user.getId();
        this.type = user.getType();
        this.username = user.getUsername();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
