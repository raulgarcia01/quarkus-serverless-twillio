package com.java.playground.entity;

import com.java.playground.enums.MessageType;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Template message entity collection structure
 */
@Data
@MongoEntity(collection = "messages_base")
public class TemplateEntity {

    public ObjectId id;

    @NotEmpty(message = "Insert template name")
    @Size(min = 5, message = "Insert at least 5 characters in template name")
    public String templateName;

    @NotEmpty(message = "Insert template text message")
    @Size(min = 15, message = "Insert at least 15 characters in template message")
    public String templateText;

    @Pattern(regexp = "^(http:\\/\\/|https:\\/\\/)?([\\w\\Q$-_+!*'(),%\\E]+\\.)+(\\w{2,63})(:\\d{1,4})?([\\w\\Q/$-_+!*'(),%\\E]+\\.?[\\w])*\\/?$", message = "Must be a valid URL path")
    public String imageURL;

    @NotNull(message = "Must included message type")
    public MessageType type;

}
