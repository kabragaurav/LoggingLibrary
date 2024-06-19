package models;

import enums.Level;
import utils.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;

import static enums.Level.getHigherOrEqualLevels;
import static utils.LoggerConstants.LOG_MSG_PLACE_HOLDER;

/**
 * @author gauravkabra
 * @since 2024
 */

public class ConsoleSink implements Sink {

    @Override
    public void log(Level level, String message, LocalDateTime startTime, LocalDateTime endTime) {
        List<Level> effectiveLogLevels = getHigherOrEqualLevels(level);

        for (Level effectiveLogLevel : effectiveLogLevels) {
            String msg = String.format(LOG_MSG_PLACE_HOLDER,
                    effectiveLogLevel,
                    startTime,
                    endTime,
                    DateTimeUtil.getLocalDateTimeDiffInMs(endTime, startTime),
                    message);
            System.out.println(msg);
        }
    }
}
