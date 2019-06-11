package com.juliuskrah;

import javax.inject.Singleton;

import reactor.core.publisher.Mono;

@Singleton
public class StatusServiceImpl implements StatusService<Status> {
    public Mono<Status> save(Status status) {
        return Mono.just(status);
    }
}