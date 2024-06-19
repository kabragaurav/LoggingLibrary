import enums.Level;
import models.ConsoleSink;
import models.Logger;
import models.TextFileSink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static utils.LoggerConstants.DEFAULT_LOG_LEVEL;
import static utils.LoggerConstants.DEFAULT_SINK;

/**
 * @author gauravkabra
 * @since 2024
 */


public class LoggerTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Since Logger is singleton, reset logger field to null
     * before running each test
     */
    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        Field loggerField = Logger.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(null, null);
    }

    @Test
    public void testLoggerCreationWithDefaults() {
        Logger logger = Logger.getLogger();
        assertEquals(DEFAULT_LOG_LEVEL, logger.getLogLevel());
        assertEquals(DEFAULT_SINK, logger.getSink());
    }

    @Test
    public void testLoggerCreationWithCustoms() {
        Logger logger = Logger.getLogger(Level.WARN, new TextFileSink("./test/log_testTxtFileLogger.txt"));
        assertEquals(Level.WARN, logger.getLogLevel());
    }

    @Test
    public void testLoggerCreationWithModifications() {
        Logger logger = Logger.getLogger();
        logger.setLogLevel(Level.FATAL);
        assertEquals(500, logger.getLogLevel().getPriority());
        assertEquals(DEFAULT_SINK, logger.getSink());
    }

    @Test
    public void testConsoleLogger() {
        LocalDateTime startTime = LocalDateTime.now();
        System.setOut(new PrintStream(outputStreamCaptor));

        Logger logger = Logger.getLogger();
        logger.setLogLevel(Level.ERROR);
        logger.setSink(new ConsoleSink());

        logger.log("This is a console logger test", startTime, LocalDateTime.now());
        assertTrue(outputStreamCaptor.toString().trim().contains("This is a console logger test"));
        System.setOut(originalOut);
    }

    @Test
    public void testTxtFileLogger() throws IOException {
        LocalDateTime startTime = LocalDateTime.now();

        Logger logger = Logger.getLogger();
        logger.setLogLevel(Level.INFO);
        logger.setSink(new TextFileSink("./test/log_testTxtFileLogger.txt"));

        logger.log("This is a txt file logger test", startTime, LocalDateTime.now());

        // now file should be created, if was not there already
        Path filePath = Paths.get(".", "./test/log_testTxtFileLogger.txt");
        assertTrue(Files.exists(filePath));

        // check size is not 0B
        assertNotEquals(0, Files.size(filePath));

        // check contains a string
        List<String> lines = Files.readAllLines(filePath);

        // Check if the file contains the search string
        boolean containsString = false;
        boolean containsLevel = false;
        boolean containsDebug = false;
        for (String line : lines) {
            if (line.contains("This is a txt file logger test")) {
                containsString = true;
            }
            if (line.contains(Level.FATAL.toString())) {
                containsLevel = true;
            }
            if (line.contains(Level.DEBUG.toString())) {
                containsDebug = true;
            }
        }
        assertTrue(containsString && containsLevel && !containsDebug);
    }
}
