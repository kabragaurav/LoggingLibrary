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
     * • details required for sink (eg file location))
     */

    private static Map<String, Configuration> configurationsMap = new HashMap<>();

    public void setupSink(Level loggingLevel, SinkType sinkType, String details) {
        String key = loggingLevel.toString() + "_" + sinkType.toString();
        configurationsMap.putIfAbsent(key, new Configuration(loggingLevel, sinkType, details));
    }

    /**
     * You specify message content, level and namespace while sending a message
     * Sink need not be mentioned while sending a message to the logger library.
     */
    public void log(Message message, LocalDateTime startTime, LocalDateTime endTime) {

        Optional<Sink> optionalSink = Optional.ofNullable(message.getSink());

        // Sink need not be mentioned while sending a message to the logger library.
        SinkType sinkType = optionalSink
                .map(Sink::getSinkType)
                .orElse(null);

        // A message level is tied to a sink.
        List<Level> loggingLevels = optionalSink
                .map(Sink::getLevels)
                .orElse(List.of(DEFAULT_LOG_LEVEL));

        String key = message.getLevel() + "_" + sinkType;

        Configuration configuration = configurationsMap.get(key);

        Level level = message.getLevel();
        List<Level> effectiveLoggingLevels = Level.getHigherOrEqualLevels(level);

        for (Level effectiveLoggingLevel : effectiveLoggingLevels) {
                Set<SinkType> sinkTypes = new HashSet<>(DEFAULT_LEVEL_TO_SINK_TYPES.get(effectiveLoggingLevel));
                sinkTypes.add(sinkType);
                for (SinkType sinkTyps : sinkTypes) {
                    Writer writer = WriterFactory.getWriter(sinkTyps);
                    writer.write(message, configuration, startTime, endTime);
                }
        }
    }
}
