package io.github.rhkiswani.javaff.reflection;

import io.github.rhkiswani.javaff.beans.EmployeeX;
import io.github.rhkiswani.javaff.detector.ApiDetectorUtil;
import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import io.github.rhkiswani.javaff.reflection.exception.ReflectionException;
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
        try{
            ReflectionUtil.getCallerClass(-1);
        }catch (Exception e){
            assertThat(e).isInstanceOf(IllegalParamException.class).hasMessage("Number Of Levels should be greater or equal 0");
        }
        try{
            ReflectionUtil.getCallerClass(10000);
        }catch (Exception e){
            assertThat(e).isInstanceOf(IllegalParamException.class).hasMessageContaining("StackTrace MaxSize is");
        }
    }


    @Test
    public void testExceptions() {
        try {
            reflectionHelper.getField(ReflectionTest.class, "non_exist_field");
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("non_exist_field not found");
        }
        try {
            reflectionHelper.getField(null, "non_exist_field");
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("target class cant be null");
        }
        // these fields will be ignored because they are added by frameworks
        try {
            reflectionHelper.getField(X.class, "$jacocoData");
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("$jacocoData not found");
        }
        try {
            reflectionHelper.getField(X.class, "__cobertura_counters");
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("__cobertura_counters not found");
        }
        try {
            reflectionHelper.getField(X.class, "this$");
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("this$ not found");
        }

        try {
            reflectionHelper.forName("this$");
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("java.lang.ClassNotFoundException: this$");
        }
        try {
            reflectionHelper.getFields(null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("Class cant be null");
        }
        try {
            reflectionHelper.getFieldValue(null, "");
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("io.github.rhkiswani.javaff.reflection.exception.ReflectionException: Object cant be null");
        }
        try {
            reflectionHelper.getFieldValue(new EmployeeX(), null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("io.github.rhkiswani.javaff.reflection.exception.ReflectionException: Field Name cant be null");
        }
        try {
            reflectionHelper.getFieldValue(new EmployeeX(), "kiswani");
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("io.github.rhkiswani.javaff.reflection.exception.ReflectionException: kiswani not found");
        }
        assertThat(Throwable.class.isInstance(new SmartException(""))).isEqualTo(true);
    }

    @Test
    public void testSetStaticFinalVal() {
        reflectionHelper.setStaticFieldValue(ApiDetectorUtil.class, "isSlf4jAvailable", false);
        assertThat(ApiDetectorUtil.isSlf4jAvailable()).isEqualTo(false);
        reflectionHelper.setStaticFieldValue(ApiDetectorUtil.class, "isSlf4jAvailable", true);
        assertThat(ApiDetectorUtil.isSlf4jAvailable()).isEqualTo(true);
        try{
            reflectionHelper.setStaticFieldValue(ApiDetectorUtil.class, "xxxx", false);
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("xxxx not found");
        }
        try{
            ReflectionUtil.getCallerClass(-1);
        }catch (Exception e){
            assertThat(e).isInstanceOf(IllegalParamException.class).hasMessage("Number Of Levels should be greater or equal 0");
        }
        try{
            ReflectionUtil.getCallerClass(10000);
        }catch (Exception e){
            assertThat(e).isInstanceOf(IllegalParamException.class).hasMessageContaining("StackTrace MaxSize is");
        }
    }

    @Test
    public void testIsPresent() {
        assertThat(ReflectionUtil.isPresent(Integer.class.getName())).isEqualTo(true);
        assertThat(ReflectionUtil.isPresent("com.xyz.xyz")).isEqualTo(false);
    }

    @Test
    public void testSetVal() {
        X x = new X();
        reflectionHelper.setFieldValue(x, "privateStr", "Value");
        assertThat(x.privateStr).isEqualTo("Value");
        try{
            reflectionHelper.setFieldValue(null, "privateStr", "Value");
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("target object cant be null");
        }
        try{
            reflectionHelper.setFieldValue(x, "privateStr", Integer.valueOf(1));
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("class java.lang.Integer is not fit to class java.lang.String");
        }
        try{
            reflectionHelper.setFieldValue(x, "privatePrimitive", null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("java.lang.IllegalArgumentException: Can not set int field io.github.rhkiswani.javaff.reflection.ReflectionTest$X.privatePrimitive to null value");
        }
        try{
            ReflectionUtil.setFieldValue(x, "privatePrimitive", null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("java.lang.IllegalArgumentException: Can not set int field io.github.rhkiswani.javaff.reflection.ReflectionTest$X.privatePrimitive to null value");
        }
        ReflectionUtil.setFieldValue(x, "privatePrimitive", 1);
        assertThat(ReflectionUtil.getFieldValue(x, "privatePrimitive")).isEqualTo(1);

        try{
            ReflectionUtil.setFieldValue(x, "privatePrimitive", new EmployeeX());
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("class io.github.rhkiswani.javaff.beans.EmployeeX is not fit to int");
        }

        try{
            ReflectionUtil.setStaticFieldValue(ApiDetectorUtil.class, "isJPAAvailable", new EmployeeX());
        }catch (Exception e){
            assertThat(e).isInstanceOf(ReflectionException.class).hasMessage("Can not set static java.lang.Boolean field io.github.rhkiswani.javaff.detector.ApiDetectorUtil.isJPAAvailable to io.github.rhkiswani.javaff.beans.EmployeeX");
        }

        ReflectionUtil.setStaticFieldValue(ApiDetectorUtil.class, "isJPAAvailable", ApiDetectorUtil.isJPAAvailable());
    }

    @Test
    public void testFactory() {
        assertThat(ReflectionHelpersFactory.instance() == ReflectionHelpersFactory.instance()).isEqualTo(true);
    }

    private class X {
        private Object $jacocoData;
        private Object __cobertura_counters;
        private Object this$;
        private String privateStr;
        private int privatePrimitive;
        public void doX(){
            assertThat(ReflectionUtil.getCallerClass(1)).isEqualTo(ReflectionTest.class);
        }
    }
}
