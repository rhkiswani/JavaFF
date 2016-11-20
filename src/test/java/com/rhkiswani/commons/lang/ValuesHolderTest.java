package com.rhkiswani.commons.lang;

import com.rhkiswani.commons.beans.withIdAnnotation.withEqualsAnnotation.EmployeeByIdAnnotation;
import com.rhkiswani.commons.beans.withIdAnnotation.withEqualsAnnotation.PersonByIdAnnotation;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValuesHolderTest {

    private EmployeeByIdAnnotation e;
    private EmployeeByIdAnnotation e1;
    @Before
    public void setup(){
        e = new EmployeeByIdAnnotation();
        e.setId(100000);
        e.setName("Kiswani");
        e.setEmpId(1000);
        e1 = new EmployeeByIdAnnotation();
        e1.setId(100000);
        e1.setName("Kiswani123");
        e1.setTransientVal("Kiswani");
        e1.staticVal = "Static";
    }
    @Test
    public void testEquals() throws Exception {
        assertThat(e.equals(e1)).isEqualTo(true);
        e.setId(4);
        assertThat(e.equals(e1)).isEqualTo(false);
    }

    @Test
    public void testHashcode() throws Exception {
        int hashCode = new HashCodeBuilder().append(100000).append("Kiswani123").hashCode();
        assertThat(e1.hashCode()).isEqualTo(hashCode);
        e1.setName("New Name");
        assertThat(e1.hashCode()).isNotEqualTo(hashCode);
    }

    @Test
    public void testClone() throws Exception {
        EmployeeByIdAnnotation clone = e1.clone();
        assertThat(clone.getName()).isEqualTo(e1.getName());
        assertThat(clone.getId()).isEqualTo(e1.getId());
        assertThat(clone.getEmpId()).isEqualTo(e1.getEmpId());
        assertThat(clone.getTransientVal()).isEqualTo(e1.getTransientVal());
        assertThat(clone.staticVal).isEqualTo(e1.staticVal);
    }

    @Test
    public void testToString() throws Exception {
        e1.setEmpId(500000);
        assertThat(e1.toString()).isEqualTo("EmployeeByIdAnnotation[empId=500,000, id=100,000, name=Kiswani123, transientVal=Kiswani, staticVal=Static]");
    }

    @Test
    public void testX() throws Exception {
        assertThat(PersonByIdAnnotation.class.isInstance(e1)).isEqualTo(true);
    }
}
