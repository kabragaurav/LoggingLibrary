package factories;

import models.Configuration;
import models.Message;
import utils.DateTimeUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * @author gauravkabra
 * @since 2024
 */

public class TextFileWriter implements Writer {

    @Override
    public void write(Message message, Configuration configuration, LocalDateTime startTime, LocalDateTime endTime) {
        String txtFileLocation = configuration.getTxtFileLocation();
        Path filePath = Paths.get(txtFileLocation);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFileLocation, true))) {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            String line = String.format("PRIORITY: %s, START TIME: %s, END TIME: %s, TIME TAKEN: %s ms, MESSAGE: %s, FROM: %s",
                    message.getLevel().getPriority(),
                    startTime.toString(), endTime.toString(),
                    DateTimeUtil.getLocalDateTimeDiffInMs(endTime, startTime),
                    message.getContent(), message.getNamespace());
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
