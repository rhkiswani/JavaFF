package io.github.rhkiswani.javaff.beans;

public class Employee extends Person<Employee> {

    private int empId;

    public Employee() {

    }

    public Employee(int empId) {
        super();
        this.empId = empId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
