package com.totospz.eshop.config.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    @Getter @Setter
    private String resource;
    @Getter @Setter
    private String property;
    @Getter @Setter
    private String value;

    public EntityNotFoundException(String resource, String property, String value) {
        super(String.format("%s no encontrado con: %s = '%s'",resource,property,value));
        this.resource = resource;
        this.property = property;
        this.value = value;
    }

    public EntityNotFoundException(String resource, String property, Integer value) {
        super(String.format("%s no encontrado con: %s = '%s'",resource,property,value));
        this.resource = resource;
        this.property = property;
        this.value = value.toString();
    }

}
