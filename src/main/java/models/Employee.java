package models;

import java.time.LocalDate;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final LocalDate hireDate;
    private final String jobId;
    private final int salary;

    public Employee(String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate, String jobId, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.jobId = jobId;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String getJobId() {
        return jobId;
    }

    public int getSalary() {
        return salary;
    }
}
