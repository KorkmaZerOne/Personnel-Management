package be.intecbrussel.model;

import java.time.LocalDate;
import java.util.Date;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String phoneNumberICE;
    private LocalDate dateOfBirth ;
    private double salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberICE() {
        return phoneNumberICE;
    }

    public void setPhoneNumberICE(String phoneNumberICE) {
        this.phoneNumberICE = phoneNumberICE;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", PhoneNumberICE='" + phoneNumberICE + '\'' +
                ", DateOfBirth=" + dateOfBirth +
                ", Salary=" + salary +
                '}';
    }
}
