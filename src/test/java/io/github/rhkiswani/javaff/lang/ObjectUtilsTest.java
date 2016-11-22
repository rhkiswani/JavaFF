package io.github.rhkiswani.javaff.lang;

import io.github.rhkiswani.javaff.beans.withEqualsAnnotation.Employee;
import io.github.rhkiswani.javaff.beans.withEqualsAnnotation.Person;
import io.github.rhkiswani.javaff.beans.withIdAnnotation.withEqualsAnnotation.EmployeeByIdAnnotation;
import io.github.rhkiswani.javaff.beans.withIdAnnotation.withEqualsAnnotation.PersonByIdAnnotation;
import io.github.rhkiswani.javaff.beans.withOutAnnotation.withEqualsAnnotation.EmployeeX;
import io.github.rhkiswani.javaff.lang.utils.ObjectUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectUtilsTest {

    @Test
    public void testEqualsWithSuperClassWithoutAnnotation() throws Exception {
        EmployeeX e = new EmployeeX();
        e.setId(1);
        e.setName("Kiswani");
        e.setEmpId(1000);
        EmployeeX e1 = new EmployeeX();
        e1.setId(1);
        e1.setName("Kiswani123");
        assertThat(ObjectUtils.isEqual(e, e1)).isEqualTo(false);
        e1.setId(-1);
        e1.setEmpId(1000);
        e1.setName("Kiswani");
        assertThat(ObjectUtils.isEqual(e, e1)).isEqualTo(false);
        e1.setId(1);
        assertThat(ObjectUtils.isEqual(e, e1)).isEqualTo(true);
        e1.setName(null);
        e.setName(null);
        assertThat(ObjectUtils.isEqual(e, e1)).isEqualTo(true);
    }

    @Test
    public void testEqualsWithSuperClassByEqualsAnnotation() throws Exception {
        Employee e = new Employee();
        e.setId(1);
        e.setName("Kiswani");
        e.setEmpId(1000);
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("Kiswani123");
        assertThat(ObjectUtils.isEqual(e, e1)).isEqualTo(true);
    }

    @Test
    public void testEqualsByEqualsAnnotation() throws Exception {
        Person e = new Person();
        e.setId(1);
        e.setName("Kiswani");
        Person e1 = new Person();
        e1.setId(1);
        e1.setName("Kiswani123");
        assertThat(ObjectUtils.isEqual(e, e1)).isEqualTo(true);
        Employee e2 = new Employee();
        e2.setId(1);
        e2.setName("Kiswani123");
        assertThat(ObjectUtils.isEqual(e, e2)).isEqualTo(false);
        Person e3 = new Employee();
        e3.setId(1);
        e3.setName("Kiswani123");
        assertThat(ObjectUtils.isEqual(e, e3)).isEqualTo(false);
        assertThat(ObjectUtils.isEqual(null, null)).isEqualTo(true);
        assertThat(ObjectUtils.isEqual(e, null)).isEqualTo(false);
        assertThat(ObjectUtils.isEqual(null, e)).isEqualTo(false);
    }

    @Test
    public void testEqualsWithSuperClassByIdAnnotation() throws Exception {
        EmployeeByIdAnnotation e = new EmployeeByIdAnnotation();
        e.setId(1);
        e.setName("Kiswani");
        e.setEmpId(1000);
        EmployeeByIdAnnotation e1 = new EmployeeByIdAnnotation();
        e1.setId(1);
        e1.setName("Kiswani123");
        assertThat(ObjectUtils.isEqual(e, e1)).isEqualTo(true);
    }

    @Test
    public void testEqualsByIdAnnotation() throws Exception {
        PersonByIdAnnotation e = new PersonByIdAnnotation();
        e.setId(1);
        e.setName("Kiswani");
        PersonByIdAnnotation e1 = new PersonByIdAnnotation();
        e1.setId(1);
        e1.setName("Kiswani123");
        assertThat(ObjectUtils.isEqual(e, e1)).isEqualTo(true);
        EmployeeByIdAnnotation e2 = new EmployeeByIdAnnotation();
        e2.setId(1);
        e2.setName("Kiswani123");
        assertThat(ObjectUtils.isEqual(e, e2)).isEqualTo(false);
        PersonByIdAnnotation e3 = new EmployeeByIdAnnotation();
        e3.setId(1);
        e3.setName("Kiswani123");
        assertThat(ObjectUtils.isEqual(e, e3)).isEqualTo(false);
        assertThat(ObjectUtils.isEqual(null, null)).isEqualTo(true);
        assertThat(ObjectUtils.isEqual(e, null)).isEqualTo(false);
        assertThat(ObjectUtils.isEqual(null, e)).isEqualTo(false);
    }

    @Test
    public void testEmptyClass() throws Exception {
        assertThat(ObjectUtils.isEqual(new EmptyClass(), new EmptyClass())).isEqualTo(false);
    }

    @Test
    public void testHashcode() throws Exception {
        assertThat(ObjectUtils.toHashCode(null)).isEqualTo(-1);
    }

    private class EmptyClass{

    }
}
