package com.java.playground.resource;

import com.java.playground.entity.TemplateEntity;
import org.mapstruct.Mapper;
/**
 * Template utility mapper to work with template domain
 * and template entity objects
 */
@Mapper(componentModel = "cdi")
public interface TemplateMapper {
    TemplateEntity toEntity(Template domain);

    Template toDomain(TemplateEntity entity);
}
