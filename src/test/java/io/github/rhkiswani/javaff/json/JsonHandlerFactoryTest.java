package io.github.rhkiswani.javaff.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.github.rhkiswani.javaff.beans.EmployeeX;
import io.github.rhkiswani.javaff.factory.exceptions.NoImplementationFoundException;
import io.github.rhkiswani.javaff.json.annotations.GsonBean;
import io.github.rhkiswani.javaff.json.annotations.JacksonBean;
import io.github.rhkiswani.javaff.json.exceptions.JsonException;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
public class JsonHandlerFactoryTest {

    private EmployeeX employeeX = null;
    @Before
    public void setUp(){
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
    public void testObjectToJson() {
        String jacksonJson = "{\"id\":10,\"name\":\"Kiswani\",\"empId\":1000}";
        String gsonJson = "{\"empId\":1000,\"id\":10,\"name\":\"Kiswani\"}";
        assertThat(JsonHandlerFactory.getJsonHandler(GsonBean.class).toJson(employeeX)).isEqualTo(gsonJson);
        assertThat(JsonHandlerFactory.getJsonHandler(JacksonBean.class).toJson(employeeX)).isEqualTo(jacksonJson);
    }

    @Test
    public void testJsonHandlerFactory() {
        assertThat(JsonHandlerFactory.instance()).isEqualTo(JsonHandlerFactory.instance());
    }

    @Test
    public void testJsonToObject() {
        String jacksonJson = "{\"id\":10,\"name\":\"Kiswani\",\"empId\":1000}";
        String gsonJson = "{\"empId\":1000,\"id\":10,\"name\":\"Kiswani\"}";
        assertThat(JsonHandlerFactory.getJsonHandler(GsonBean.class).fromJson(gsonJson,EmployeeX.class)).isEqualTo(employeeX);
        assertThat(JsonHandlerFactory.getJsonHandler(GsonBean.class).fromJson(jacksonJson,EmployeeX.class)).isEqualTo(employeeX);
        assertThat(JsonHandlerFactory.getJsonHandler(JacksonBean.class).fromJson(gsonJson,EmployeeX.class)).isEqualTo(employeeX);
        assertThat(JsonHandlerFactory.getJsonHandler(JacksonBean.class).fromJson(jacksonJson,EmployeeX.class)).isEqualTo(employeeX);
    }

    @Test
    public void testNoImplementationFoundException() {
        JsonHandlerFactory.instance().remove(Object.class);
        try {
            JsonHandlerFactory.getJsonHandler(Object.class);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NoImplementationFoundException.class).hasMessage("No implementation found for JsonHandlerFactory you need to set implementation through JsonHandlerFactory.instance().add or add https://mvnrepository.com/artifact/com.google.code.gson/gson to your classpath");
        }
    }
    @Test
    public void testJsonException() {
        String gsonJson = "{\"empId\":1000,\"id\":10,\"name";
        try {
            JsonHandlerFactory.getJsonHandler(GsonBeanX.class).fromJson(gsonJson, Integer.class);
        } catch (Exception e){
            assertThat(e).isInstanceOf(JsonException.class).hasMessage("java.lang.IllegalStateException: Expected an int but was BEGIN_OBJECT at line 1 column 2 path $");
        }
        try {
            JsonHandlerFactory.getJsonHandler(JacksonBeanX.class).fromJson(gsonJson, Integer.class);
        } catch (Exception e){
            assertThat(e).isInstanceOf(JsonException.class).hasMessage("Can not deserialize instance of java.lang.Integer out of START_OBJECT token\n" +
                    " at [Source: {\"empId\":1000,\"id\":10,\"name; line: 1, column: 1]");
        }
        try {
            JsonHandlerFactory.getJsonHandler(JacksonBeanX.class).toJson(new BorderLayout());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JsonException.class);
        }
        try {
            JsonHandlerFactory.getJsonHandler(GsonBeanX.class).toJson(new BorderLayout());
        } catch (Exception e){
            assertThat(e).isInstanceOf(JsonException.class);
        }
    }

    @GsonBean
    private class GsonBeanX extends EmployeeX{

    }

    @JacksonBean
    private class JacksonBeanX extends EmployeeX{

    }

}
