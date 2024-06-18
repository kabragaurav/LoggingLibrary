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
        // here details refer to txtFileLocation
        private String details;
        // and for DB related details like uname, pwd, url

        public Configuration(Level loggingLevel, SinkType sinkType, String details) {
            this.loggingLevel = loggingLevel;
            this.sinkType = sinkType;
            this.details = details;
        }

        public String getDetails() {
            return details;
        }
    }
