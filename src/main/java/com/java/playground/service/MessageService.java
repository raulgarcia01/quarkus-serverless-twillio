package com.java.playground.service;

import com.java.playground.entity.TemplateEntity;
import com.java.playground.enums.MessageType;
import com.java.playground.exception.ServiceException;
import com.java.playground.repository.TemplateRepository;
import com.java.playground.resource.FreeMessage;
import com.java.playground.resource.LoadMessage;
import com.java.playground.resource.MessageResult;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.quarkus.logging.Log;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.net.URI;
import java.util.Objects;

/**
 * Message service to handle message business logic
 */
@ApplicationScoped
@AllArgsConstructor
public class MessageService {
    /**
     * Twilio ACCOUNT_SID key value
     */
    public static final String ACCOUNT_SID = "REPLACE_ACCOUNT_SID";
    /**
     * Twilio AUTH_TOKEN key value
     */
    public static final String AUTH_TOKEN = "REPLACE_AUTH_TOKEN";

    /**
     * Template repository injection
     */
    private final TemplateRepository templateRepository;


    /**
     * Twilio init method to authenticate and create a
     * session to send SMS messages
     */
    private void initMessageService(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    /**
     * Send custom messages using free style body parameters
     * @param smsPayload Payload object
     * @return Message result queue
     */
    public MessageResult sendFreeMessage(FreeMessage smsPayload) {
        initMessageService();
        Message message;
        if (smsPayload.getUriImage() == null) {
            message = Message.creator(
                            new PhoneNumber(smsPayload.getRemittent()),
                            new PhoneNumber(smsPayload.getSender()),
                            smsPayload.getMessage())
                    .create();
        } else {
            message = Message.creator(
                            new PhoneNumber(smsPayload.getRemittent()),
                            new PhoneNumber(smsPayload.getSender()),
                            smsPayload.getMessage())
                    .setMediaUrl(URI.create(smsPayload.getUriImage()))
                    .create();
        }
        return new MessageResult(message.getSid(), message.getStatus().toString());
    }

    /**
     * Send template messages using body parameters
     * @param loadPayload Payload object
     * @return Message result queue
     */
    public MessageResult sendTemplateMessage(LoadMessage loadPayload) {
        initMessageService();
        Message message;
        TemplateEntity entity = templateRepository.find("templateName", loadPayload.getTemplateName()).firstResult();
        if (!Objects.isNull(entity)) {
            if(entity.getType().equals(MessageType.REG)) {
                message = Message.creator(
                                new PhoneNumber(loadPayload.getRemittent()),
                                new PhoneNumber(loadPayload.getSender()),
                                entity.getTemplateText().replace("[user_name]", loadPayload.getUserName()))
                        .create();
            } else {
                message = Message.creator(
                                new PhoneNumber(loadPayload.getRemittent()),
                                new PhoneNumber(loadPayload.getSender()),
                                entity.getTemplateText().replace("[user_name]", loadPayload.getUserName()))
                                .setMediaUrl(URI.create(entity.getImageURL()))
                        .create();
            }
        } else {
            Log.info(String.format("Error template name not found: %s", loadPayload.getTemplateName()));
            throw new ServiceException(String.format("Template name not found: [%s]", loadPayload.getTemplateName()));
        }
        return new MessageResult(message.getSid(), message.getStatus().toString());
    }
}
