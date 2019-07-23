package com.juliuskrah;

import static java.util.UUID.randomUUID;

import java.util.UUID;

import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class StatusServiceImpl implements StatusService<Status, UUID> {

    @Override
    public Single<Status> save(Status status) {
      status.setId(randomUUID());
	  return Single.just(status);
	}

}