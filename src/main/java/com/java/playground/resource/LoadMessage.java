package com.java.playground.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Template style message body domain object with validations
 */
@Data
@AllArgsConstructor
public class LoadMessage {

    @NotEmpty(message = "Insert the sender number")
    @Pattern(regexp = "^([+]{1})([0-9]{11})$", message = "Must be US number format")
    private String sender;

    @NotEmpty(message = "Insert remittent number")
    @Pattern(regexp = "^([+]{1})([0-9]{11})$", message = "Must be US number format")
    private String remittent;

    @NotEmpty(message = "Insert user name")
    @Size(min = 3, message = "Insert at least 3 characters in user name")
    public String userName;

    @NotEmpty(message = "Insert template name")
    @Size(min = 5, message = "Insert at least 5 characters in template name")
    public String templateName;
}
