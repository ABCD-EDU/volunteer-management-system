package models;

public class Role {
    private String name, desc;
    private boolean needsAuth;

    public Role(String name, String desc, boolean needsAuth) {
        this.name = name;
        this.desc = desc;
        this.needsAuth = needsAuth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isNeedsAuth() {
        return needsAuth;
    }

    public void setNeedsAuth(boolean needsAuth) {
        this.needsAuth = needsAuth;
    }
}
