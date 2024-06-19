package models;

import enums.Level;

import java.time.LocalDateTime;

/**
 * @author gauravkabra
 * @since 2024
 */

public interface Sink {
    void log(Level level, String message, LocalDateTime startTime, LocalDateTime endTime);
}
