package utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author gauravkabra
 * @since 2024
 */

public class DateTimeUtil {

    public static long getLocalDateTimeDiffInMs(LocalDateTime endDateTime, LocalDateTime startDateTime) {
        long startMillis = startDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        long endMillis = endDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        return endMillis - startMillis;
    }
}
