package models;

import java.time.LocalDate;

public class PersonalInfo {
    private char gender;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;

    public PersonalInfo() { }

    public PersonalInfo(char gender, LocalDate birthDate, String address, String phoneNumber) {
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
