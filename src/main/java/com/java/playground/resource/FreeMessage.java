package com.java.playground.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Free style message body domain object with validations
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeMessage {
    public FreeMessage(String sender, String remittent, String message) {
        this.sender = sender;
        this.remittent = remittent;
        this.message = message;
    }

    @NotEmpty(message = "Insert the sender number")
    @Pattern(regexp = "^([+]{1})([0-9]{11})$", message = "Must be US number format")
    private String sender;

    @NotEmpty(message = "Insert remittent number")
    @Pattern(regexp = "^([+]{1})([0-9]{11})$", message = "Must be US number format")
    private String remittent;

    @NotEmpty(message = "Insert custom message to be send")
    @Size(min = 5, message = "Insert at least 5 characters the custom message to be send")
    private String message;

    @Pattern(regexp = "^(http:\\/\\/|https:\\/\\/)?([\\w\\Q$-_+!*'(),%\\E]+\\.)+(\\w{2,63})(:\\d{1,4})?([\\w\\Q/$-_+!*'(),%\\E]+\\.?[\\w])*\\/?$", message = "Must be a valid URL path")
    private String uriImage;

}
