package utils;

import enums.Level;
import models.ConsoleSink;
import models.Sink;

/**
 * @author gauravkabra
 * @since 2024
 */

public interface LoggerConstants {
    Level DEFAULT_LOG_LEVEL = Level.DEBUG;
    Sink DEFAULT_SINK = new ConsoleSink();
    String LOG_MSG_PLACE_HOLDER = "[%s] START TIME: %s, END TIME: %s, TIME TAKEN (ms): %s, MESSAGE: %s";
}
