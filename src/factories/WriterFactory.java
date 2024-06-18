package factories;

import enums.SinkType;

import static enums.SinkType.*;

/**
 * @author gkabra
 * @since 250
 */

public class WriterFactory {

    public static Writer getWriter(SinkType sinkType) {
        switch (sinkType) {
            case CONSOLE:
                return new ConsoleWriter();
            // rest cases like file writer, DB writer
            default:
                return new ConsoleWriter();
        }
    }
}
