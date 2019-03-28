package io.resk.message.command.repository;

import java.io.Serializable;

import javax.inject.Singleton;

import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.resk.message.command.domain.Role;
import io.resk.message.command.domain.User;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final PgPool client;

	@Override
	public Single<User> save(User user) {
		Tuple tuple = Tuple.tuple();
		tuple.addUUID(user.getId());
		tuple.addString(user.getEmail());
		tuple.addString(user.getUsername());
		tuple.addString(user.getPassword());
		tuple.addBoolean(user.isEnabled());
		tuple.addBoolean(user.isAccountExpired());
		tuple.addBoolean(user.isAccountLocked());
		tuple.addBoolean(user.isPasswordExpired());

		return client
				.rxPreparedQuery("INSERT INTO users (id, email, username, password, enabled, accountExpired, "
						+ "accountLocked, passwordExpired) VALUES ($1, $2 $3, $4, $5, $6, $7, $8)", tuple)
				.map(rows -> user);
	}

	@Override
	public Single<User> save(String email, String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Single<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Single<User> findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Single<User> saveUserWithRole(User user, Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flowable<String> findAllRolesByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
