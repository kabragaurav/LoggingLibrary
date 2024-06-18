import enums.Level;
import enums.SinkType;
import models.Message;
import models.Sink;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author gauravkabra
 * @since 2024
 */


public class LoggerTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testConsoleLogger() {
        LocalDateTime startTime = LocalDateTime.now();
        Logger logger = new Logger();
        System.setOut(new PrintStream(outputStreamCaptor));
        Sink sink = new Sink(SinkType.CONSOLE, List.of(Level.DEBUG));
        Message message = new Message("This is a logger test",
                Level.DEBUG,
                sink,
                "test_nspace");
        LocalDateTime endTime = LocalDateTime.now();
        logger.log(message, startTime, endTime);
        assertNotEquals(0, outputStreamCaptor.toString().trim());
    }
}
