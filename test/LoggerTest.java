import enums.Level;
import enums.SinkType;
import models.Configuration;
import models.Message;
import models.Sink;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gauravkabra
 * @since 2024
 */


public class LoggerTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void testWithoutSetupThrowsException() {
        LocalDateTime startTime = LocalDateTime.now();
        System.setOut(new PrintStream(outputStreamCaptor));
        Logger logger = new Logger();
        Sink sink = new Sink(SinkType.CONSOLE, Set.of(Level.DEBUG));
        Message message = new Message("This is a logger test",
                Level.DEBUG,
                sink,
                "test_nspace");
        LocalDateTime endTime = LocalDateTime.now();
        assertThrows(Exception.class, () -> logger.log(message, startTime, endTime));
        System.setOut(originalOut);
    }

    @Test
    public void testConsoleLogger() throws Exception {
        LocalDateTime startTime = LocalDateTime.now();
        System.setOut(new PrintStream(outputStreamCaptor));
        Logger logger = new Logger();

        logger.setupSink(Level.DEBUG);
        logger.setupSink(Level.INFO);
        logger.setupSink(Level.WARN);
        logger.setupSink(Level.ERROR);
        logger.setupSink(Level.FATAL);

        Sink sink = new Sink(SinkType.CONSOLE, Set.of(Level.DEBUG));
        Message message = new Message("This is a console logger test",
                Level.DEBUG,
                sink,
                "test_nspace");
        LocalDateTime endTime = LocalDateTime.now();
        logger.log(message, startTime, endTime);
        assertTrue(outputStreamCaptor.toString().trim().contains(message.getContent()));
        System.setOut(originalOut);
    }

    @Test
    public void testTxtFileLogger() throws Exception {
        LocalDateTime startTime = LocalDateTime.now();
        Logger logger = new Logger();

        logger.setupSink(Level.DEBUG, SinkType.TXT_FILE, "./log_testTxtFileLogger.txt");
        logger.setupSink(Level.INFO, SinkType.TXT_FILE, "./log_testTxtFileLogger.txt");
        logger.setupSink(Level.WARN, SinkType.TXT_FILE, "./log_testTxtFileLogger.txt");
        logger.setupSink(Level.ERROR, SinkType.TXT_FILE, "./log_testTxtFileLogger.txt");
        logger.setupSink(Level.FATAL, SinkType.TXT_FILE, "./log_testTxtFileLogger.txt");

        Sink sink = new Sink(SinkType.TXT_FILE, Set.of(Level.DEBUG));
        Message message = new Message("This is a txt file logger test",
                Level.DEBUG,
                sink,
                "test_nspace");
        LocalDateTime endTime = LocalDateTime.now();
        logger.log(message, startTime, endTime);

        // now file should be created, if was not there already
        Path filePath = Paths.get(".", "./log_testTxtFileLogger.txt");
        assertTrue(Files.exists(filePath));

        // check size is not 0B
        assertNotEquals(0, Files.size(filePath));

        // check contains a string
        List<String> lines = Files.readAllLines(filePath);
        // Check if the file contains the search string
        boolean containsString = false;
        for (String line : lines) {
            if (line.contains("This is a txt file logger test")) {
                containsString = true;
                break;
            }
        }
        assertTrue(containsString);
    }
}
