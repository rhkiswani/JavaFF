package io.github.rhkiswani.jutils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.github.rhkiswani.jutils.beans.withOutAnnotation.withEqualsAnnotation.EmployeeX;
import io.github.rhkiswani.jutils.reflection.ReflectionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { ReflectionUtil.class })
public class JsonHandlerFactoryTest {

    private EmployeeX employeeX = null;
    @Before
    public void setup(){
        employeeX = new EmployeeX();
        employeeX.setEmpId(1000);
        employeeX.setName("Kiswani");
        employeeX.setId(10);
    }
    @Test
    public void testJsonHandler() {
        assertThat(JsonHandlerFactory.getHandler().getImplementation()).isInstanceOf(ObjectMapper.class);
        mockStatic(ReflectionUtil.class);
        when(ReflectionUtil.isPresent("com.fasterxml.jackson.databind.ObjectMapper")).thenReturn(false);
        assertThat(JsonHandlerFactory.getHandler().getImplementation()).isInstanceOf(Gson.class);
    }

    @Test
    public void testJson() {
        String empAsJson = "{\"id\":10,\"name\":\"Kiswani\",\"empId\":1000}";
        assertThat(JsonHandlerFactory.getHandler().toJson(employeeX)).isEqualTo(empAsJson);
        assertThat(JsonHandlerFactory.getHandler().fromJson(empAsJson, EmployeeX.class)).isEqualTo(employeeX);
    }

}
