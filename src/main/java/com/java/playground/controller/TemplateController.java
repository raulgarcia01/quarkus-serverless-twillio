package com.java.playground.controller;

import com.java.playground.entity.TemplateEntity;
import com.java.playground.resource.Template;
import com.java.playground.service.TemplateService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Template controller resource to expose CRUD REST API endpoints
 * and save templates messages information.
 */
@Path("/api/templates")
@AllArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TemplateController {

    /**
     * Injecting the template service component
     */
    private final TemplateService templateService;

    /**
     * Provide a list of templates pull from mongodb collection
     * and can be filtered using the following parameters
     * @param templateName Template name
     * @param templateType Template type
     * @return List of templates
     */
    @GET
    public Response getTemplates(
            @QueryParam("name") @DefaultValue("") String templateName,
            @QueryParam("type") @DefaultValue("") String templateType
    ) {
        try {
            return Response.ok(templateService.getTemplates(templateName, templateType)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * Add a template information to templates collection
     * @param template Template domain object
     * @return Template created with object id
     */
    @POST
    public Response addTemplate(@Valid Template template) {
        try {
            final TemplateEntity saved = templateService.addTemplate(template);
            return Response.status(Response.Status.CREATED).entity(saved).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * Delete a template from collection base on object id
     * @param id Object id key
     * @return 204 response to indicated deletion
     */
    @DELETE
    public Response deleteTemplate(@QueryParam("id") ObjectId id) {
        try {
            templateService.deleteTemplate(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
