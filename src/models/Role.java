package models;

public class Role {

    private int roleId;
    private String name;
    private String description;
    private String degreeProgram;
    private int year;
    private boolean needsVerification;

    public Role(int roleId, String name, String description, String degree_program,
                int year, boolean needs_verification) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        this.degreeProgram = degree_program;
        this.year = year;
        this.needsVerification = needs_verification;
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

    public String getDegreeProgram() {
        return degreeProgram;
    }

    public void setDegreeProgram(String degreeProgram) {
        this.degreeProgram = degreeProgram;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isNeedsVerification() {
        return needsVerification;
    }

    public void setNeedsVerification(boolean needsVerification) {
        this.needsVerification = needsVerification;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
