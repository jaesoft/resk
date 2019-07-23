package com.juliuskrah;

import java.io.Serializable;

import io.micronaut.http.annotation.Get;
import io.reactivex.Maybe;

public abstract class GenericController<T, ID extends Serializable> {
    private final StatusService<T, ID> service;

    public GenericController(StatusService<T, ID> service) {
        this.service = service;
    }

    @Get("/{id}")
    public Maybe<T> find(ID id) {
        System.out.println("id " + id.getClass()); // java.lang.String
        return service.find(id);
    }
}