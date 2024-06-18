package models;

import enums.Level;
import enums.SinkType;

/**
 * @author gauravkabra
 * @since 2024
 */

public class Configuration {
    private Level loggingLevel;
    private SinkType sinkType;
    private String txtFileLocation;
    // future - and for DB related details like uname, pwd, url add more vars

    public Configuration(Level loggingLevel, SinkType sinkType) {
        this.loggingLevel = loggingLevel;
        this.sinkType = sinkType;
    }

    public Configuration(Level loggingLevel, SinkType sinkType, String txtFileLocation) {
        this(loggingLevel, sinkType);
        this.txtFileLocation = txtFileLocation;
    }

    public Level getLoggingLevel() {
        return loggingLevel;
    }

    public SinkType getSinkType() {
        return sinkType;
    }

    public String getTxtFileLocation() {
            return txtFileLocation;
        }
    }
