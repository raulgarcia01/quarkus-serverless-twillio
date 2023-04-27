package com.java.playground.controller;

import com.java.playground.resource.FreeMessage;
import com.java.playground.resource.LoadMessage;
import com.java.playground.resource.MessageResult;
import com.java.playground.service.MessageService;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Message controller resource to expose REST API endpoints
 * to send simple and multimedia SMS messages
 */
@Path("/api/messages")
@AllArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageController {

    /**
     * Injecting the message service component
     */
    private final MessageService messageService;

    /**
     * Endpoint to send free and custom messages to recipients
     * @param smsPayload Body payload to send free style messages
     * @return Message queue information
     */
    @POST
    @Path("/freeStyleSMS")
    public Response freeStyleSMS(@Valid FreeMessage smsPayload) {
        try {
            MessageResult messageResult = messageService.sendFreeMessage(smsPayload);
            return Response.status(Response.Status.OK).entity(messageResult).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * Endpoint to send template messages to recipients
     * @param loadMessage Body payload to send template messages
     * @return Message queue information
     */
    @POST
    @Path("/templateStyleSMS")
    public Response templateStyleSMS(@Valid LoadMessage loadMessage) {
        try {
            MessageResult messageResult = messageService.sendTemplateMessage(loadMessage);
            return Response.status(Response.Status.OK).entity(messageResult).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
