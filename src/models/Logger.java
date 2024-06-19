package models;

import enums.Level;

import java.time.LocalDateTime;

import static utils.LoggerConstants.DEFAULT_LOG_LEVEL;
import static utils.LoggerConstants.DEFAULT_SINK;

/**
 * @author gauravkabra
 * @since 2024
 */

public class Logger {

    private static Logger logger;
    private Level logLevel;
    private Sink sink;

    private Logger(Level logLevel, Sink sink) {
        this.logLevel = logLevel;
        this.sink = sink;
    }

    public static Logger getLogger() {
        return getLogger(DEFAULT_LOG_LEVEL, DEFAULT_SINK);
    }

    public static Logger getLogger(Level level, Sink sink) {
        if (null == logger) {
            logger = new Logger(level, sink);
        }
        return logger;
    }

    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    public void setSink(Sink sink) {
        this.sink = sink;
    }

    public Level getLogLevel() {
        return logLevel;
    }

    public Sink getSink() {
        return sink;
    }

    public void log(String message, LocalDateTime startTime, LocalDateTime endTime) {
        sink.log(logLevel, message, startTime, endTime);
    }

    public void log(Level level, String message, LocalDateTime startTime, LocalDateTime endTime) {
        sink.log(level, message, startTime, endTime);
    }
}
