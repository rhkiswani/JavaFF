package com.rhkiswani.commons.beans.withOutAnnotation.withEqualsAnnotation;

public class EmployeeX extends PersonX<EmployeeX> {

    private int empId;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
