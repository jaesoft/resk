package com.juliuskrah;

import java.io.Serializable;

import io.reactivex.Single;

public interface StatusService<T, ID extends Serializable> {
    Single<T> save(T status);
}