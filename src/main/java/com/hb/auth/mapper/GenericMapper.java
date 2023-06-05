package com.hb.auth.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface GenericMapper<E , Q, R> {
    E requestToEntity(Q request);

    R entityToResponse(E entity);

    default List<R> entitiesToResponses(Collection<E> entities){
        if(entities == null) return Collections.emptyList();

        return entities.stream().map(this::entityToResponse).toList();
    }

    default List<E> requestsToEntities(Collection<Q> views){
        if(views == null) return Collections.emptyList();

        return views.stream().map(this::requestToEntity).toList();
    }
}
