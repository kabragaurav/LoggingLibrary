package enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gauravkabra
 * @since 2024
 */

public enum Level {
    DEBUG(100),
    INFO(200),
    WARN(300),
    ERROR(400),
    FATAL(500),
    ;

    // the order of priority / ordinal
    private final int priority;

    Level(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public static List<Level> getHigherOrEqualLevels(Level obtained) {
        List<Level> effectiveLoggingLevels = new ArrayList<>();
        for (Level level : Level.values()) {
            if (level.getPriority() >= obtained.getPriority()) {
                effectiveLoggingLevels.add(level);
            }
        }
        return effectiveLoggingLevels;
    }

    @Override
    public String toString() {
        return this.name() + "(" + priority + ")";
    }
}
