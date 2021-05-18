package models;

public class Role {

    private int roleId;
    private String name;
    private String description;
    private String degree_program;
    private int year;
    private boolean needs_verification;

    public Role(int roleId, String name, String description, String degree_program,
                int year, boolean needs_verification) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        this.degree_program = degree_program;
        this.year = year;
        this.needs_verification = needs_verification;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public String getDegree_program() {
        return degree_program;
    }

    public void setDegree_program(String degree_program) {
        this.degree_program = degree_program;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isNeeds_verification() {
        return needs_verification;
    }

    public void setNeeds_verification(boolean needs_verification) {
        this.needs_verification = needs_verification;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
