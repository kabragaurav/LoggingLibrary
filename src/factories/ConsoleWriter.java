package factories;

import models.Configuration;
import models.Message;
import utils.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * @author gauravkabra
 * @since 2024
 */

public class ConsoleWriter implements Writer {

    @Override
    public void write(Message message, Configuration configuration, LocalDateTime startTime, LocalDateTime endTime) {
        System.out.println(
                String.format("PRIORITY: %s, START TIME: %s, END TIME: %s, TIME TAKEN: %s ms, MESSAGE: %s, FROM: %s",
                        message.getLevel().getPriority(),
                        startTime.toString(), endTime.toString(),
                        DateTimeUtil.getLocalDateTimeDiffInMs(endTime, startTime),
                        message.getContent(), message.getNamespace())
        );
    }
}
