package io.github.rhkiswani.jutils.lang;

import io.github.rhkiswani.jutils.beans.ValuesHolder;
import io.github.rhkiswani.jutils.beans.withIdAnnotation.withEqualsAnnotation.EmployeeByIdAnnotation;
import io.github.rhkiswani.jutils.beans.withIdAnnotation.withEqualsAnnotation.PersonByIdAnnotation;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        assertThat(e1.toString()).isEqualTo("EmployeeByIdAnnotation[id=100,000]");
        ToStringTestClass x = new ToStringTestClass();
        x.i = 123123123;
        x.f = 123123123;
        x.d = 123123123;
        x.date = new SimpleDateFormat("yyyy-MM-dd").parse("2016-11-21");
        x.c = '\n';
        x.l = 123123123;
        x.s = "Kiswani";
        assertThat(x.toString()).isEqualTo("ToStringTestClass[i=123,123,123, f=123,123,120, d=123,123,123, date=11/21/16 12:00 AM, c=\n, l=123,123,123, s=Kiswani]");
    }

    @Test
    public void testX() throws Exception {
        assertThat(PersonByIdAnnotation.class.isInstance(e1)).isEqualTo(true);
    }

    private class ToStringTestClass extends ValuesHolder{
        int i;
        float f;
        double d;
        Date date;
        char c;
        long l;
        String s;
    }
}
