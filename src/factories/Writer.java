package factories;

import enums.Level;
import models.Message;
import models.Configuration;
import java.time.LocalDateTime;

/**
 * @author gauravkabra
 * @since 2024
 */

public interface Writer {
    void write(Message message, Level level, Configuration configuration, LocalDateTime startTime, LocalDateTime endTime);
}
