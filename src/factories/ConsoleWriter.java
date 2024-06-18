package factories;

import enums.Level;
import models.Configuration;
import models.Message;
import utils.DateTimeUtil;

import java.time.LocalDateTime;

import static utils.LoggerConstants.LOG_FILL_IN_MSG;

/**
 * @author gauravkabra
 * @since 2024
 */

public class ConsoleWriter implements Writer {

    @Override
    public void write(Message message, Level level, Configuration configuration, LocalDateTime startTime, LocalDateTime endTime) {
        assert null != configuration;
        System.out.println(
                String.format(LOG_FILL_IN_MSG,
                        message.getLevel().getPriority(), level.getPriority(),
                        startTime.toString(), endTime.toString(),
                        DateTimeUtil.getLocalDateTimeDiffInMs(endTime, startTime),
                        message.getContent(), message.getNamespace())
        );
    }
}
