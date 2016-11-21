package io.github.rhkiswani.javaff.beans.withEqualsAnnotation;

public class Employee extends Person<Employee>{

    private int empId;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
