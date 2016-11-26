
package io.github.rhkiswani.javaff.logging;

import io.github.rhkiswani.javaff.log.Log;
import io.github.rhkiswani.javaff.log.LogFactory;
import org.junit.Test;
import uk.org.lidalia.slf4jext.Level;
import uk.org.lidalia.slf4jtest.LoggingEvent;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggersTest {

    private static final Log LOGGER = LogFactory.getLogger(LoggersTest.class);
    private static final TestLogger TEST_LOGGER = TestLoggerFactory.getTestLogger(LoggersTest.class);

    @Test
    public void testInfoLog() {
        LOGGER.info("{0}", "Hi");
        testLog("Hi", Level.INFO);
    }

    @Test
    public void testDebugLog() {
        LOGGER.debug("{0}", "Hi");
        testLog("Hi", Level.DEBUG);
    }

    @Test
    public void testErrorLog() {
        LOGGER.error("{0}", "Hi");
        testLog("Hi", Level.ERROR);
    }

    @Test
    public void testWarnLog() {
        LOGGER.warn("{0}", "Hi");
        testLog("Hi", Level.WARN);
    }

    private void testLog(String msg, Level level) {
        assertThat(TEST_LOGGER.getLoggingEvents().size()).isEqualTo(1);
        LoggingEvent[] objects = TEST_LOGGER.getLoggingEvents().toArray(new LoggingEvent[TEST_LOGGER.getLoggingEvents().size()]);
        assertThat(objects[0].getMessage()).isEqualTo(msg);
        assertThat(objects[0].getLevel()).isEqualTo(level);
        TestLoggerFactory.clear();
    }


}
