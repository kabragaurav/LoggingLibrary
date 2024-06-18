import enums.Level;
import enums.SinkType;
import models.Message;
import models.Sink;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author gkabra
 * @since 250
 */


public class LoggerTest {

    public static void main(String[] args) {
        LocalDateTime startTime = LocalDateTime.now();
        Logger logger = new Logger();
        Sink sink = new Sink(SinkType.CONSOLE, List.of(Level.DEBUG));
        Message message = new Message("This is a logger test",
                Level.DEBUG,
                sink,
                "test_nspace");
        LocalDateTime endTime = LocalDateTime.now();
        logger.log(message, startTime, endTime);
    }
}
