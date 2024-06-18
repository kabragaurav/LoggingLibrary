package enums;

import java.util.HashSet;
import java.util.Set;

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

    // the order of priority
    private int priority;

    Level(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public static Set<Level> getHigherOrEqualLevels(Set<Level> obtainedLevels) {
        Set<Level> effectiveLoggingLevels = new HashSet<>();
        for (Level obtained : obtainedLevels) {
            effectiveLoggingLevels.addAll(getHigherOrEqualLevels(obtained));
        }
        return effectiveLoggingLevels;
    }

    private static Set<Level> getHigherOrEqualLevels(Level obtained) {
        Set<Level> effectiveLoggingLevels = new HashSet<>();
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
