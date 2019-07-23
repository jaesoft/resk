package com.juliuskrah;

import java.util.UUID;

import javax.inject.Singleton;

import io.reactivex.Maybe;

@Singleton
public class StatusServiceImpl implements StatusService<Status, UUID> {

    @Override
    public Maybe<Status> find(UUID id) {
		return Maybe.empty();
	}

}