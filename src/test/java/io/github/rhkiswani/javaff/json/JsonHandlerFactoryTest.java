package io.github.rhkiswani.javaff.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.github.rhkiswani.javaff.beans.withOutAnnotation.withEqualsAnnotation.EmployeeX;
import io.github.rhkiswani.javaff.json.annotations.GsonBean;
import io.github.rhkiswani.javaff.json.annotations.JacksonBean;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(JsonHandlerFactory.getJsonHandler(JacksonBean.class).getImplementation()).isInstanceOf(ObjectMapper.class);
        assertThat(JsonHandlerFactory.getJsonHandler(GsonBean.class).getImplementation()).isInstanceOf(Gson.class);
        try {
            JsonHandlerFactory.getJsonHandler(null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(IllegalParamException.class).hasMessage("Target Class cant be null");
        }
    }

    @Test
    public void testJson() {
        String jacksonJson = "{\"id\":10,\"name\":\"Kiswani\",\"empId\":1000}";
        String gsonJson = "{\"empId\":1000,\"id\":10,\"name\":\"Kiswani\"}";
        assertThat(JsonHandlerFactory.getJsonHandler(GsonBean.class).toJson(employeeX)).isEqualTo(gsonJson);
        assertThat(JsonHandlerFactory.getJsonHandler(JacksonBean.class).toJson(employeeX)).isEqualTo(jacksonJson);
        assertThat(JsonHandlerFactory.getJsonHandler(GsonBeanX.class).toJson(employeeX)).isEqualTo(gsonJson);
        assertThat(JsonHandlerFactory.getJsonHandler(JacksonBeanX.class).toJson(employeeX)).isEqualTo(jacksonJson);
    }

    @GsonBean
    private class GsonBeanX extends EmployeeX{

    }

    @JacksonBean
    private class JacksonBeanX extends EmployeeX{

    }

}
