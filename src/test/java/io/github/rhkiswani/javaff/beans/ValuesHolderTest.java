package io.github.rhkiswani.javaff.beans;

import io.github.rhkiswani.javaff.lang.annotations.EqualsField;
import io.github.rhkiswani.javaff.lang.annotations.HashcodeField;
import io.github.rhkiswani.javaff.lang.annotations.ToString;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ValuesHolderTest {

    private EmployeeByIdAnnotation e;
    private EmployeeByIdAnnotation e1;
    @Before
    public void setUp(){
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
        e1.setName(null);
        e1.setId(null);
        assertThat(e1.hashCode()).isEqualTo(-1);
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
        ToStringTestClass stringTestClass = new ToStringTestClass();
        stringTestClass.date = new SimpleDateFormat("yyyy-MM-dd").parse("2016-11-21");
        assertThat(stringTestClass.toString()).isEqualTo("ToStringTestClass[i=123,123,123, f=123,123,120, d=123,123,123, date=11/21/16 12:00 AM, c=\n, l=123,123,123, s=Kiswani, arr=[1,2,3], coll=[[1,2,3]]]");
        ToStringTestClassX x = new ToStringTestClassX();
        assertThat(x.toString()).isEqualTo("ToStringTestClassX[i=123,123,123, f=123,123,120]");
        ToStringTestClassXX xx = new ToStringTestClassXX();
        assertThat(xx.toString()).isEqualTo("ToStringTestClassXX[f=4,444,111]");
        ToStringTestClassXXX xxx = new ToStringTestClassXXX();
        assertThat(xxx.toString()).isEqualTo("ToStringTestClassXXX[i=10,000]");
        ToStringTestClassXXXX xxxx = new ToStringTestClassXXXX();
        assertThat(xxxx.toString()).isEqualTo("ToStringTestClassXXXX[d=99,900,011]");
    }

    @Test
    public void testX() throws Exception {
        assertThat(PersonByIdAnnotation.class.isInstance(e1)).isEqualTo(true);
    }

    private class ToStringTestClass extends ValuesHolder{
        private int i = 123123123;
        private float f = 123123123;
        private double d = 123123123;
        private Date date = new Date();
        private char c = '\n';
        private long l = 123123123;
        private String s = "Kiswani";
        private int[] arr = new int[]{1, 2, 3};
        private Collection coll = Arrays.asList(arr);
    }

    private class ToStringTestClassX extends ValuesHolder{
        @ToString
        private int i = 123123123;
        @ToString
        private float f = 123123123;
        private double d = 123123123;
    }

    private class ToStringTestClassXX extends ValuesHolder{
        private int i = 9090909;
        @HashcodeField
        private float f = 4444111;
        private double d = 123123123;
    }

    private class ToStringTestClassXXX extends ValuesHolder{
        @EqualsField
        private int i = 10000;
        private float f = 123123123;
        private double d = 123123123;
    }

    private class ToStringTestClassXXXX extends ValuesHolder{
        private int i = 50000;
        private float f = 123123123;
        @Id
        private double d = 99900011;
    }
}
