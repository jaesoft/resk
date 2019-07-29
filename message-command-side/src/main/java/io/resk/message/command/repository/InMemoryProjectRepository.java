package io.resk.message.command.repository;

import java.util.UUID;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.resk.message.command.domain.Project;

@Singleton
public class InMemoryProjectRepository implements ProjectRepository {

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Maybe<Project> find(UUID projectId) {
		// TODO Auto-generated method stub
		return Maybe.empty();
	}

	@Override
	public Maybe<Project> find(String username, UUID projectId) {
		// TODO Auto-generated method stub
		return Maybe.empty();
	}

	@Override
	public Flowable<Project> findAllByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flowable<Project> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Single<Project> save(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

}
