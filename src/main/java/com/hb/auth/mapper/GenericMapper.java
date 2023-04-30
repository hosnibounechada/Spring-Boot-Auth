package com.hb.auth.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericMapper<ENTITY , REQUEST, RESPONSE> {
    ENTITY requestToEntity(REQUEST request);

    RESPONSE entityToResponse(ENTITY entity);

    default List<RESPONSE> entitiesToResponses(Collection<ENTITY> entities){
        if(entities == null) return null;

        return entities.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    default List<ENTITY> requestsToEntities(Collection<REQUEST> views){
        if(views == null) return null;

        return views.stream().map(this::requestToEntity).collect(Collectors.toList());
    }
}
