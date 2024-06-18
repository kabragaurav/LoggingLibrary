import enums.Level;
import enums.SinkType;
import factories.Writer;
import factories.WriterFactory;
import models.Configuration;
import models.Message;
import models.Sink;

import java.time.LocalDateTime;
import java.util.*;

import static utils.LoggerConstants.*;

/**
 * @author gauravkabra
 * @since 2024
 */

public class Logger {

    /**
     * Specifies all the details required to use the logger library.
     *
     * Library can accept one or more configuration for an application
     * One configuration per association of message level and sink
     *
     * You may consider logger configuration as a key-value pair
     * Example:
     * • time format
     * • logging level
     * • sink type
     * • details required for sink (e.g. file location)
     */

    private static Map<String, Configuration> configurationsMap = new HashMap<>();

    public void setupSink(Level loggingLevel) {
        String key = loggingLevel.toString() + MAP_KEY_SEP + DEFAULT_SINK_TYPE;
        // put replenishes existing key, if any
        configurationsMap.put(key, new Configuration(loggingLevel, DEFAULT_SINK_TYPE));
    }

    public void setupSink(Level loggingLevel, SinkType sinkType, String txtFileLocation) {
        String key = loggingLevel.toString() + MAP_KEY_SEP + sinkType.toString();
        // put replenishes existing key, if any
        configurationsMap.put(key, new Configuration(loggingLevel, sinkType, txtFileLocation));
    }

    /**
     * You specify message content, level and namespace while sending a message
     * Sink need not be mentioned while sending a message to the logger library.
     */
    public void log(Message message, LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        // Requires configuration during sink setup
        if (configurationsMap.isEmpty()) {
            throw new Exception(NO_SINK_CONFIG_FOUND);
        }
        // Sink need not be mentioned while sending a message to the logger library.
        // get sink mentioned in message, if any
        Optional<Sink> optionalSink = Optional.ofNullable(message.getSink());
        SinkType sinkType = optionalSink
                .map(Sink::getSinkType)
                .orElse(DEFAULT_SINK_TYPE);

        // A message level is tied to a sink.
        Set<Level> loggingLevels = new HashSet<>(optionalSink
                .map(Sink::getLevels)
                .orElse(Set.of(DEFAULT_LOG_LEVEL)));

        Level level = message.getLevel();

        // Message levels above a given message level should be logged
        loggingLevels.add(level);
        Set<Level> effectiveLoggingLevels = Level.getHigherOrEqualLevels(loggingLevels);
        effectiveLoggingLevels.addAll(loggingLevels);

        for (Level effectiveLoggingLevel : effectiveLoggingLevels) {
            String key = effectiveLoggingLevel.toString() + MAP_KEY_SEP + sinkType;
            Configuration configuration = configurationsMap.get(key);
            Writer writer = WriterFactory.getWriter(sinkType);
            writer.write(message, effectiveLoggingLevel, configuration, startTime, endTime);
        }
    }
}
