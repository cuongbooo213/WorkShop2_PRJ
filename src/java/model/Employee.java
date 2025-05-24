package model;

import java.math.BigDecimal;
import java.util.Date;
import model.Departments;
import model.Jobs;

public class Employee {

    int employeeId;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    Date hireDate;
    Jobs job;  // Đối tượng Job
    Departments department;  // Đối tượng Department
    BigDecimal salary;
    Integer managerId;

    // Getter và setter cho employeeId
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    // Getter và setter cho firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter và setter cho lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter và setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter và setter cho phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter và setter cho hireDate
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    // Getter và setter cho job (Job là đối tượng)
    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    // Getter và setter cho department (Department là đối tượng)
    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    // Getter và setter cho salary
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    // Getter và setter cho managerId
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}
