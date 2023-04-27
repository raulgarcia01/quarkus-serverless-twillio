package com.java.playground.enums;

/**
 * Messages enum type to list SMS type messages
 * to be sent.
 */
public enum MessageType {
    /**
     * Identify simple SMS message
     */
    REG("REGULAR"),
    /**
     * Identify multimedia SMS message
     */
    MUL("MULTIMEDIA");

    public final String value;

    private MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static MessageType findByValue(String value) {
        MessageType result = null;
        for (MessageType val : values()) {
            if (val.getValue().equalsIgnoreCase(value)) {
                result = val;
                break;
            }
        }
        return result;
    }
}
