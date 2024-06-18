package utils;


import enums.Level;
import enums.SinkType;

/**
 * @author gauravkabra
 * @since 2024
 */

public interface LoggerConstants {

    SinkType DEFAULT_SINK_TYPE = SinkType.CONSOLE;

    // By default, lowest priority of logging
    Level DEFAULT_LOG_LEVEL = Level.DEBUG;
    String MAP_KEY_SEP = "_";
    String LOG_FILL_IN_MSG = "MSG PRIORITY: %s, PRIORITY: %s, START TIME: %s, END TIME: %s, TIME TAKEN: %s ms, MESSAGE: %s, FROM: %s";

    // Exceptions
    String NO_SINK_CONFIG_FOUND = "Please first config the logging lib before using";
} 