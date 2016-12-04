package io.github.rhkiswani.javaff.factory;

import io.github.rhkiswani.javaff.factory.exceptions.NoImplementationFoundException;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFactoryTest {

    @Test
    public void testRegistry() throws Exception {
        assertThat(TestFactory.instance()).isNotNull();
        assertThat(TestFactory.instance() == TestFactory.instance()).isEqualTo(true);
        assertThat(TestFactory.instance().listImplementations().size()).isEqualTo(10);
        TestFactory.instance().add(Character.class, new DummyObject());
        assertThat(TestFactory.instance().listImplementations().size()).isEqualTo(11);
        TestFactory.instance().remove(char.class);
        assertThat(TestFactory.instance().listImplementations().size()).isEqualTo(10);
    }

    @Test
    public void testOverride() throws Exception {
        try{
            TestFactory.instance().add(Date.class, new DummyObject());
        } catch (Exception e){
            assertThat(e).isInstanceOf(IllegalParamException.class).hasMessage("class java.util.Date is already exist please use overrideImp method instated");
        }
        TestFactory.instance().overrideImp(char.class, new DummyObject());
        testPrimitive(char.class, Character.class);
    }

    @Test
    public void testGet() throws Exception {
        DummyObject dummyObjectForNumber = TestFactory.instance().create(Number.class);
        DummyObject dummyObject2ForNumber = TestFactory.instance().create(Number.class);
        assertThat(dummyObjectForNumber).isNotNull();
        assertThat(dummyObject2ForNumber).isNotNull();
        assertThat(dummyObjectForNumber.toString()).isEqualTo(dummyObject2ForNumber.toString());

        testPrimitive(byte.class, Byte.class);
        testPrimitive(short.class, Short.class);
        testPrimitive(int.class, Integer.class);
        testPrimitive(long.class, Long.class);
        testPrimitive(float.class, Float.class);
        testPrimitive(double.class, Double.class);
        testPrimitive(boolean.class, Boolean.class);
    }

    @Test
    public void testGetWithNoImpl() throws Exception {
        try{
        TestFactory.instance().create(void.class);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NoImplementationFoundException.class).hasMessage("No implementation found for type [void] you need to set implementation through TestFactory.instance().add");
        }
    }

    private void testPrimitive(Class primitive, Class wrapperClass) {
        DummyObject dummyObject = TestFactory.instance().create(wrapperClass);
        assertThat(dummyObject).isNotNull();
        DummyObject dummyObject1 = TestFactory.instance().create(primitive);
        assertThat(dummyObject1).isNotNull();
        assertThat(dummyObject1).isEqualTo(dummyObject);
    }

    private static class TestFactory extends AbstractFactory<DummyObject> {

        private static TestFactory instance = new TestFactory();

        private TestFactory(){
            add(Date.class, new DummyObject());
            add(String.class, new DummyObject());
            add(Number.class, new DummyObject());
            add(Byte.class, new DummyObject());
            add(short.class, new DummyObject());
            add(int.class, new DummyObject());
            add(Long.class, new DummyObject());
            add(Float.class, new DummyObject());
            add(Double.class, new DummyObject());
            add(Boolean.class, new DummyObject());
        }

        public static TestFactory instance(){
            return instance;
        }

    }

    private static class DummyObject extends Object{

    }

//    @After
//    public void tearDown(){
//        TestFactory.instance().remove(Date.class);
//        TestFactory.instance().remove(String.class);
//        TestFactory.instance().remove(Number.class);
//        TestFactory.instance().remove(Byte.class);
//        TestFactory.instance().remove(Character.class);
//        TestFactory.instance().remove(short.class);
//        TestFactory.instance().remove(int.class);
//        TestFactory.instance().remove(Long.class);
//        TestFactory.instance().remove(Float.class);
//        TestFactory.instance().remove(Double.class);
//        TestFactory.instance().remove(Boolean.class);
//    }
}
