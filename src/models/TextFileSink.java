package models;

import enums.Level;
import utils.DateTimeUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import static enums.Level.getHigherOrEqualLevels;
import static utils.LoggerConstants.LOG_MSG_PLACE_HOLDER;

/**
 * @author gauravkabra
 * @since 2024
 */

public class TextFileSink implements Sink {

    private String txtFileLocation;

    public TextFileSink(String txtFileLocation) {
        this.txtFileLocation = txtFileLocation;
    }

    @Override
    public void log(Level level, String message, LocalDateTime startTime, LocalDateTime endTime) {
        List<Level> effectiveLogLevels = getHigherOrEqualLevels(level);
        try (PrintWriter writer = new PrintWriter(new FileWriter(txtFileLocation, true))) {
            for (Level effectiveLogLevel : effectiveLogLevels) {
                String msg = String.format(LOG_MSG_PLACE_HOLDER,
                        effectiveLogLevel,
                        startTime,
                        endTime,
                        DateTimeUtil.getLocalDateTimeDiffInMs(endTime, startTime),
                        message);
                writer.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
