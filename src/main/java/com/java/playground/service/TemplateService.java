package com.java.playground.service;

import com.java.playground.entity.TemplateEntity;
import com.java.playground.enums.MessageType;
import com.java.playground.exception.ServiceException;
import com.java.playground.repository.TemplateRepository;
import com.java.playground.resource.Template;
import com.java.playground.resource.TemplateMapper;
import io.quarkus.logging.Log;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
    Template service to handle add and delete SMS pre setup messages
**/
@ApplicationScoped
@AllArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateMapper templateMapper;

    /**
     *  Get all templates in database collection, alternative can use filters.
     * @param name Template name
     * @param type Template type
     * @return List of templates
     */
    public List<TemplateEntity> getTemplates(String name, String type) {
        if (StringUtils.isNoneBlank(name)) {
            Log.info("Getting all templates filtered by name");
            Log.info(String.format("Name: %s", name));
            return templateRepository.findByName(name);
        } else {
            if (StringUtils.isNoneBlank(type)) {
                Log.info("Getting all templates filtered by type");
                Log.info(String.format("Type: %s", type));
                return templateRepository.findByType(MessageType.findByValue(type));
            }
        }
        Log.info("Getting all templates listed in db");
        return templateRepository.listAll();
    }

    @Transactional
    public TemplateEntity addTemplate(Template template) {
        TemplateEntity entity = templateMapper.toEntity(template);
        templateRepository.persist(entity);
        return entity;
    }

    @Transactional
    public void deleteTemplate(ObjectId id){
        Optional<TemplateEntity> entity = templateRepository.findByIdOptional(id);
        if(entity.isEmpty()){
            Log.info(String.format("Error deleting with object id: %s", id.toString()));
            throw new ServiceException(String.format("No Customer found for customerId: [%s]", id.toString()));
        }
        Log.info("The template was deleted");
        templateRepository.deleteById(id);
    }


}
