package models;

// USER_ACC(user_id, username, password, type)
public class User {

    private int userId;
    private String username;
    private String password;
    private Type type;

    public enum Type {
        VOLUNTEER("volunteer"),
        ADMIN("admin");

        private final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public User(int userId, String username, String password, Type type) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(String username, String password, Type type) {
        this.userId = -1;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return this.type.toString();
    }

    public void setUserType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }

}
