
package io.github.rhkiswani.javaff.logging;

import io.github.rhkiswani.javaff.detector.ApiDetectorUtil;
import io.github.rhkiswani.javaff.log.DefaultLog;
import io.github.rhkiswani.javaff.log.LogFactory;
import io.github.rhkiswani.javaff.log.LogWrapper;
import io.github.rhkiswani.javaff.reflection.ReflectionHelper;
import io.github.rhkiswani.javaff.reflection.ReflectionHelpersFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class DefaultLoggersTest {
    private ReflectionHelper helper = ReflectionHelpersFactory.getReflectionHelper(DefaultLoggersTest.class);
    @Before
    public void setUp(){
        helper.setStaticFieldValue(ApiDetectorUtil.class, "isSlf4jAvailable", Boolean.FALSE);
    }

    @Test
    public void testDefaultLogger(){
        assertThat(LogFactory.getLogger(DefaultLoggersTest.class)).isInstanceOf(LogWrapper.class);
        assertThat(helper.getFieldValue(LogFactory.getLogger(DefaultLoggersTest.class), "log")).isInstanceOf(DefaultLog.class);
        //to fix coverage
        LogFactory.getLogger(DefaultLoggersTest.class).info("");
        LogFactory.getLogger(DefaultLoggersTest.class).debug("");
        LogFactory.getLogger(DefaultLoggersTest.class).warn("");
        LogFactory.getLogger(DefaultLoggersTest.class).error("");
    }

    @After
    public void afterTest(){
        helper.setStaticFieldValue(ApiDetectorUtil.class, "isSlf4jAvailable", Boolean.TRUE);
    }
}
