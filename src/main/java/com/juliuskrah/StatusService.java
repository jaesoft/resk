package com.juliuskrah;

import java.io.Serializable;

import io.reactivex.Maybe;

public interface StatusService<T, ID extends Serializable> {
    Maybe<T> find(ID id);
}