package com.juliuskrah;

import reactor.core.publisher.Mono;

public interface StatusService<T> {

    default Mono<T> save(T status) {
        return Mono.just(status);
    }
}