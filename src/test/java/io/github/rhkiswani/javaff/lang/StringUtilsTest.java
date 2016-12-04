package io.github.rhkiswani.javaff.lang;

import io.github.rhkiswani.javaff.beans.EmployeeX;
import io.github.rhkiswani.javaff.lang.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {

    private EmployeeXWithArray employeeX = new EmployeeXWithArray();

    @Before
    public void setUp(){
        employeeX.setName("Kiswani");
        employeeX.setEmpId(12);
        employeeX.setId(13);
    }

    @Test
    public void testToString() {
        assertThat(StringUtils.toString(null)).isNull();
        assertThat(StringUtils.toString(employeeX)).isEqualTo("EmployeeXWithArray[empId=12, id=13, name=Kiswani]");
    }

    @Test
    public void testToStringWithArrays() {
        employeeX.setAddresses(new String[]{"Address1", "Address2"});
        assertThat(StringUtils.toString(employeeX)).isEqualTo("EmployeeXWithArray[addresses=[Address1,Address2], empId=12, id=13, name=Kiswani]");
    }

    @Test
    public void testIsEmpty() {
        assertThat(StringUtils.isEmpty(null)).isEqualTo(true);
        assertThat(StringUtils.isEmpty("")).isEqualTo(true);
        assertThat(StringUtils.isEmpty("A")).isEqualTo(false);
    }

    @Test
    public void testIsNotEmpty() {
        assertThat(StringUtils.isNotEmpty(null)).isEqualTo(false);
        assertThat(StringUtils.isNotEmpty("")).isEqualTo(false);
        assertThat(StringUtils.isNotEmpty("A")).isEqualTo(true);
    }

    @Test
    public void testIsNull() {
        assertThat(StringUtils.isNull(null)).isEqualTo(true);
        assertThat(StringUtils.isNull("")).isEqualTo(true);
        assertThat(StringUtils.isNull("A")).isEqualTo(false);
        assertThat(StringUtils.isNull("Null")).isEqualTo(true);
        assertThat(StringUtils.isNull("NULL")).isEqualTo(true);
        assertThat(StringUtils.isNull("null")).isEqualTo(true);
    }

    @Test
    public void testIsNotNull() {
        assertThat(StringUtils.isNotNull(null)).isEqualTo(false);
        assertThat(StringUtils.isNotNull("")).isEqualTo(false);
        assertThat(StringUtils.isNotNull("A")).isEqualTo(true);
        assertThat(StringUtils.isNotNull("Null")).isEqualTo(false);
        assertThat(StringUtils.isNotNull("NULL")).isEqualTo(false);
        assertThat(StringUtils.isNotNull("null")).isEqualTo(false);
    }

    private static class EmployeeXWithArray extends EmployeeX{
        String[] addresses ;
        Object[] unparsableArray ;

        public void setAddresses(String[] addresses) {
            this.addresses = addresses;
        }

        public void setUnparsableArray(Object[] unparsableArray) {
            this.unparsableArray = unparsableArray;
        }
    }
}
