package utils;


import enums.Level;
import enums.SinkType;
import models.Sink;

import java.util.List;
import java.util.Map;

/**
 * @author gauravkabra
 * @since 2024
 */

public interface LoggerConstants {

    SinkType DEFAULT_SINK_TYPE = SinkType.CONSOLE;

    // By default, lowest priority of logging
    Level DEFAULT_LOG_LEVEL = Level.DEBUG;
    String MAP_KEY_SEP = "_";

    // Exceptions
    String NO_SINK_CONFIG_FOUND = "Please first config the logging lib before using";
} 