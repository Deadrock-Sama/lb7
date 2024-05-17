package lb.project.lb6_server.lib.messages;

import java.io.Serializable;

public class KeyValuePair implements Serializable {

    private int key;
    private Serializable value;

    public KeyValuePair(int key, Serializable value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public Serializable getValue() {
        return value;
    }
}
