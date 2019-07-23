package com.juliuskrah;

import java.io.Serializable;

import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

public abstract class GenericController<T, ID extends Serializable> {
    private final StatusService<T, ID> service;

    public GenericController(StatusService<T, ID> service) {
        this.service = service;
    }

    @Post
    public Single<T> save(T entiity) {
        return service.save(entiity);
    }
}