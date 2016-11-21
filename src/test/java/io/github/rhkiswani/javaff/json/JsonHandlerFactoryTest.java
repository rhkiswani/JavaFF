package io.github.rhkiswani.javaff.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.github.rhkiswani.javaff.beans.withOutAnnotation.withEqualsAnnotation.EmployeeX;
import io.github.rhkiswani.javaff.decetor.ApiDetectorUtil;
import io.github.rhkiswani.javaff.json.annotations.GsonBean;
import io.github.rhkiswani.javaff.json.annotations.JacksonBean;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(PowerMockRunner.class)
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
    @PrepareForTest( { ApiDetectorUtil.class })
    public void testJsonHandler() {
        assertThat(JsonHandlerFactory.instance().getHandlerFor(JacksonBean.class).getImplementation()).isInstanceOf(ObjectMapper.class);
        assertThat(JsonHandlerFactory.instance().getHandlerFor(GsonBean.class).getImplementation()).isInstanceOf(Gson.class);
        try {
            JsonHandlerFactory.instance().getHandlerFor(null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(IllegalParamException.class).hasMessage("Target Class cant be null");
        }
        assertThat(JsonHandlerFactory.instance().getDefault().getImplementation()).isInstanceOf(ObjectMapper.class);
        PowerMockito.mockStatic(ApiDetectorUtil.class);
        PowerMockito.when(ApiDetectorUtil.isJacksonAvailable()).thenReturn(false);
        assertThat(JsonHandlerFactory.instance().getDefault().getImplementation()).isInstanceOf(Gson.class);
    }

    @Test
    public void testJson() {
        String jacksonJson = "{\"id\":10,\"name\":\"Kiswani\",\"empId\":1000}";
        String gsonJson = "{\"empId\":1000,\"id\":10,\"name\":\"Kiswani\"}";
        assertThat(JsonHandlerFactory.instance().getHandlerFor(GsonBean.class).toJson(employeeX)).isEqualTo(gsonJson);
        assertThat(JsonHandlerFactory.instance().getHandlerFor(JacksonBean.class).toJson(employeeX)).isEqualTo(jacksonJson);
        assertThat(JsonHandlerFactory.instance().getHandlerFor(GsonBeanX.class).toJson(employeeX)).isEqualTo(gsonJson);
        assertThat(JsonHandlerFactory.instance().getHandlerFor(JacksonBeanX.class).toJson(employeeX)).isEqualTo(jacksonJson);
        assertThat(JsonHandlerFactory.instance().getDefault().fromJson(gsonJson, EmployeeX.class)).isEqualTo(employeeX);
    }

    @GsonBean
    private class GsonBeanX extends EmployeeX{

    }

    @JacksonBean
    private class JacksonBeanX extends EmployeeX{

    }

}
