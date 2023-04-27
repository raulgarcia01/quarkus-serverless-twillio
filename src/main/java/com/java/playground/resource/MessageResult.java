package com.java.playground.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Message response object after queue message using twilio provider
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResult {

    private String smsId;

    private String status;
}
