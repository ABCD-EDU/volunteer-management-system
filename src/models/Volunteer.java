package models;

import java.time.LocalDate;

// VOLUNTEER_INFO(vol_id, first_name, last_name, birth_date,address, phone_number,year, degree_program, sex,user_id)
public class Volunteer extends User{
    private String firstName, lastName;
    private byte year;
    private String degreeProgram;

    public Volunteer(int id, String username, Type type, String fName, String lName, byte year, String degreeProgram) {
        super(id, username, type);
        this.firstName = fName;
        this.lastName = lName;
        this.year = year;
        this.degreeProgram = degreeProgram;
    }

    public Volunteer(User user, String fName, String lName, byte year, String degreeProgram) {
        super(user);
        this.firstName = fName;
        this.lastName = lName;
        this.year = year;
        this.degreeProgram = degreeProgram;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getYear() {
        return year;
    }

    public void setYear(byte year) {
        this.year = year;
    }

    public String getDegreeProgram() {
        return degreeProgram;
    }

    public void setDegreeProgram(String degreeProgram) {
        this.degreeProgram = degreeProgram;
    }
}
