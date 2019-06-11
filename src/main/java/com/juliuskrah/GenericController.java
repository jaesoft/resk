package com.juliuskrah;

import static io.micronaut.http.HttpResponse.*;

import java.io.Serializable;

import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.uri.UriBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public abstract class GenericController<T, ID extends Serializable> {
    private final StatusService<T> service;
    public abstract String getPath();
    @Post
    public Mono<HttpResponse<T>> save(@Valid @Body T entity) {
        log.info("entity '{}'",  entity);  // com.fasterxml.jackson.databind.node.ObjectNode

        return service.save(entity).map(savedEntity -> {
            var location = UriBuilder.of(getPath()) //
                            .path("1").build();
            return created(savedEntity, location);
        });
    }
}