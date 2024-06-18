package factories;

import models.Message;
import models.Configuration;


import java.time.LocalDateTime;

/**
 * @author gkabra
 * @since 250
 */

public interface Writer {
    void write(Message message, Configuration configuration, LocalDateTime startTime, LocalDateTime endTime);
}
