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

    // By default, lowest priority of logging
    Level DEFAULT_LOG_LEVEL = Level.DEBUG;

    // Routes messages to appropriate sink based on the level
    // Sink is tied to one or more message level
    // One or more message level can have the same sink
    Map<Level, List<SinkType>> DEFAULT_LEVEL_TO_SINK_TYPES = Map.of(
            Level.DEBUG, List.of(SinkType.CONSOLE),
            Level.INFO, List.of(SinkType.CONSOLE),
            Level.WARN, List.of(SinkType.TXT_FILE),
            Level.ERROR, List.of(SinkType.DATABASE),
            Level.FATAL, List.of(SinkType.DATABASE)
    );

    String DEFAULT_TXT_FILE_LOCATION = "./log_%s.txt";
} 