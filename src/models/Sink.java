package models;

import enums.Level;
import enums.SinkType;

import java.util.Set;

/**
 * @author gauravkabra
 * @since 2024
 */

public class Sink {

    private SinkType sinkType;
    private Set<Level> levels;


    public Sink(SinkType sinkType, Set<Level> levels) {
        this.sinkType = sinkType;
        this.levels = levels;
    }

    public SinkType getSinkType() {
        return sinkType;
    }

    public Set<Level> getLevels() {
        return levels;
    }
}
