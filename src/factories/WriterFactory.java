package factories;

import enums.SinkType;

import static enums.SinkType.*;

/**
 * @author gauravkabra
 * @since 2024
 */

public class WriterFactory {

    public static Writer getWriter(SinkType sinkType) {
        switch (sinkType) {
            case CONSOLE:
                return new ConsoleWriter();
            case TXT_FILE:
                return new TextFileWriter();
            // rest cases like file writer, DB writer
            default:
                return new ConsoleWriter();
        }
    }
}
