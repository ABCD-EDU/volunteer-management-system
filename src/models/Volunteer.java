package models;


import java.sql.Date;

public class Volunteer extends User{

    private int volId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String address;
    private Long phone_number;
    private Type type;
    private int year;
    private String degreeProgram;
    private Sex sex;
    private int userId;

    public enum Sex {
        MALE("male"),
        FEMALE("female");

        private final String sex;

        Sex(String sex) {
            this.sex = sex;
        }

        public String getSex() {
            return sex;
        }
    }

    public enum Type {
        STUDENT("student"),
        FACULTY("faculty");

        private final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public Volunteer(int userId, String username, String password, User.Type type, int volId, String firstName,
                     String lastName, Date birthDate, String address, Long phone_number, Type type1, int year, String degreeProgram, Sex sex, int userId1) {
        super(userId, username, password, type);
        this.volId = volId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone_number = phone_number;
        this.type = type1;
        this.year = year;
        this.degreeProgram = degreeProgram;
        this.sex = sex;
        this.userId = userId1;
    }

    public Volunteer(String username, String password, User.Type type, int volId, String firstName, String lastName,
                     Date birthDate, String address, Long phone_number, Type type1, int year, String degreeProgram, Sex sex, int userId) {
        super(username, password, type);
        this.volId = volId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone_number = phone_number;
        this.type = type1;
        this.year = year;
        this.degreeProgram = degreeProgram;
        this.sex = sex;
        this.userId = userId;
    }

    public Volunteer(String username, String password, User.Type type, String firstName, String lastName,
                     Date birthDate, String address, Long phone_number, Type type1, int year, String degreeProgram, Sex sex, int userId) {
        super(username, password, type);
        this.volId = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone_number = phone_number;
        this.type = type1;
        this.year = year;
        this.degreeProgram = degreeProgram;
        this.sex = sex;
        this.userId = userId;
    }

    public Volunteer(String username, String password, User.Type type, String firstName, String lastName,
                     Date birthDate, String address, Long phone_number, Type type1, int year, String degreeProgram, Sex sex) {
        super(username, password, type);
        this.volId = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone_number = phone_number;
        this.type = type1;
        this.year = year;
        this.degreeProgram = degreeProgram;
        this.sex = sex;
    }

    public int getVolId() {
        return volId;
    }

    public void setVolId(int volId) {
        this.volId = volId;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public Volunteer.Type getVolType() {
        return type;
    }

    public void setVolType(Volunteer.Type type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDegreeProgram() {
        return degreeProgram;
    }

    public void setDegreeProgram(String degreeProgram) {
        this.degreeProgram = degreeProgram;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "volId=" + volId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phone_number=" + phone_number +
                ", type=" + type +
                ", year=" + year +
                ", degreeProgram='" + degreeProgram + '\'' +
                ", sex=" + sex +
                ", userId=" + userId +
                '}';
    }

}
