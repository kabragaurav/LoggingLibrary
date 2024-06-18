package models;

import enums.Level;

/**
 * @author gauravkabra
 * @since 2024
 */

public class Message {
    
    private String content;
    private Level level;
    private Sink sink;
    private String namespace;


    public Message(String content, Level level, Sink sink, String namespace) {
        this.content = content;
        this.level = level;
        this.sink = sink;
        this.namespace = namespace;
    }

    public String getContent() {
        return content;
    }

    public Level getLevel() {
        return level;
    }

    public Sink getSink() {
        return sink;
    }

    public String getNamespace() {
        return namespace;
    }
}
