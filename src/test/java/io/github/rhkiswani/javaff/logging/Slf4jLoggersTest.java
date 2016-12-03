
package io.github.rhkiswani.javaff.logging;

import io.github.rhkiswani.javaff.log.Log;
import io.github.rhkiswani.javaff.log.LogFactory;
import org.junit.Before;
import org.junit.Test;
import uk.org.lidalia.slf4jext.Level;
import uk.org.lidalia.slf4jtest.LoggingEvent;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
public class Slf4jLoggersTest {

    private Log logger = null;
    private TestLogger testLogger = null;

    @Before
    public void setUp(){
        logger = LogFactory.getLogger(Slf4jLoggersTest.class);
        testLogger = TestLoggerFactory.getTestLogger(Slf4jLoggersTest.class);
    }
    @Test
    public void testInfoLog() {
        logger.info("{0}", "Hi");
        testLog("Hi", Level.INFO);
    }

    @Test
    public void testDebugLog() {
        logger.debug("{0}", "Hi");
        testLog("Hi", Level.DEBUG);
    }

    @Test
    public void testErrorLog() {
        logger.error("{0}", "Hi");
        testLog("Hi", Level.ERROR);
    }

    @Test
    public void testErrorWithExceptionLog() {
        logger.error("{0}", new NullPointerException(), "Hi");
        testLog("Hi", Level.ERROR);
    }

    @Test
    public void testWarnLog() {
        logger.warn("{0}", "Hi");
        testLog("Hi", Level.WARN);
    }

    private void testLog(String msg, Level level) {
        assertThat(testLogger.getLoggingEvents().size()).isEqualTo(1);
        LoggingEvent[] objects = testLogger.getLoggingEvents().toArray(new LoggingEvent[testLogger.getLoggingEvents().size()]);
        assertThat(objects[0].getMessage()).isEqualTo(msg);
        assertThat(objects[0].getLevel()).isEqualTo(level);
        TestLoggerFactory.clear();
    }


}
