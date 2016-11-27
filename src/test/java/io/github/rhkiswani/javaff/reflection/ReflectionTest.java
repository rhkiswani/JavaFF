package io.github.rhkiswani.javaff.reflection;

import io.github.rhkiswani.javaff.beans.EmployeeX;
import io.github.rhkiswani.javaff.exceptions.SmartException;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class ReflectionTest {

    private ReflectionHelper reflectionHelper;

    @Before
    public void setUp(){
        reflectionHelper = new DefaultReflectionHelper<EmployeeX>();
    }

    @Test
    public void testFieldsByAnnotation() {
        assertThat(reflectionHelper.scanFieldsByAnnotation(null, null)).isNull();
        assertThat(reflectionHelper.scanFieldsByAnnotation(Integer.class, null)).isNull();
        assertThat(reflectionHelper.scanFieldsByAnnotation(Integer.class, null, null)).isNull();
        assertThat(reflectionHelper.scanFieldsByAnnotation(null, Integer.class, null, null)).isNull();
    }

    @Test
    public void testGetCallerClass() {
        new X().doX();
        assertThat(Throwable.class.isInstance(new SmartException(""))).isEqualTo(true);
    }

    private class X {
        public void doX(){
            assertThat(ReflectionUtil.getCallerClass(1)).isEqualTo(ReflectionTest.class);
        }
    }
}
