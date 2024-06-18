package models;

import enums.Level;
import enums.SinkType;

import java.util.List;

/**
 * @author gauravkabra
 * @since 2024
 */

public class Sink {

    private SinkType sinkType;
    private List<Level> levels;


    public Sink(SinkType sinkType, List<Level> levels) {
        this.sinkType = sinkType;
        this.levels = levels;
    }

    public SinkType getSinkType() {
        return sinkType;
    }

    public List<Level> getLevels() {
        return levels;
    }
}
