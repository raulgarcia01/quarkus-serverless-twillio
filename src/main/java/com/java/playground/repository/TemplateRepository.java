package com.java.playground.repository;

import com.java.playground.entity.TemplateEntity;
import com.java.playground.enums.MessageType;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * Template repository and custom filters to list templates
 */
@ApplicationScoped
public class TemplateRepository implements PanacheMongoRepository<TemplateEntity> {
    public List<TemplateEntity> findByName(String name){
        return list("templateName", name);
    }
    public List<TemplateEntity> findByType(MessageType type){
        return list("type", type);
    }
}
