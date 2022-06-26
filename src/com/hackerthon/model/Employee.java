package com.hackerthon.model;

public class Employee {// Java Bean Class Employee

    private String employeeId;
    private String nameFull;
    private String address;
    private String facultyName;
    private String department;
    private String designation;

    //Default Constructor
    public Employee() {
    }

    //Full Argument Constructor
    public Employee(String employeeId, String nameFull, String address, String
            facultyName, String department, String designation) {
        this.employeeId = employeeId;
        this.nameFull = nameFull;
        this.address = address;
        this.facultyName = facultyName;
        this.department = department;
        this.designation = designation;
    }

    //Getters And Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // To String Method
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", nameFull='" + nameFull + '\'' +
                ", address='" + address + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }//End To String
}//End Employee Class
