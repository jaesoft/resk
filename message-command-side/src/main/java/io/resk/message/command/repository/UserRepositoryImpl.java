package io.resk.message.command.repository;

import java.io.Serializable;
import java.util.UUID;

import javax.inject.Singleton;

import io.reactiverse.pgclient.Row;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.PgStream;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.resk.message.command.domain.Role;
import io.resk.message.command.domain.User;
import io.resk.message.command.repository.exception.IncorrectResultSizeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final PgPool client;

	@Override
	public Single<User> save(User user) {
		Tuple tuple = Tuple.tuple();
		user.setId(UUID.randomUUID());
		tuple.addUUID(user.getId());
		tuple.addString(user.getUsername());
		tuple.addString(user.getEmail());
		tuple.addString(user.getPassword());
		tuple.addBoolean(user.isEnabled());
		tuple.addBoolean(user.isAccountExpired());
		tuple.addBoolean(user.isAccountLocked());
		tuple.addBoolean(user.isPasswordExpired());
		log.debug("Creating a new person {}", user);
		return client.rxPreparedQuery(
				"INSERT INTO users (id, username, email, password, enabled, account_expired, "
						+ "account_locked, password_expired) VALUES ($1, $2, $3, $4, $5, $6, $7, $8) RETURNING id",
				tuple).map(rows -> user);
	}

	@Override
	public Single<User> save(String email, String username, String password) {
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		return save(user);
	}

	@Override
	public Single<User> findByUsername(String username) {
		return client.rxPreparedQuery(
				"SELECT id, username, email, password, enabled, account_expired, account_locked, password_expired "
						+ "FROM users WHERE username = $1",
				Tuple.of(username)).map(rows -> {
					if (rows.rowCount() == 1) {
						User user = new User();
						for (Row row : rows.getDelegate()) {
							user.setId(row.getUUID("id"));
							user.setUsername(row.getString("username"));
							user.setEmail(row.getString("email"));
							user.setPassword("[MASKED]");
							user.setEnabled(row.getBoolean("enabled"));
							user.setAccountExpired(row.getBoolean("account_expired"));
							user.setAccountLocked(row.getBoolean("account_locked"));
							user.setPasswordExpired(row.getBoolean("password_expired"));
						}
						return user;
					} else
						throw new IncorrectResultSizeException(1, rows.rowCount());
				});
	}

	@Override
	public Single<User> findById(Serializable id) {
		return client.rxPreparedQuery(
				"SELECT id, username, email, password, enabled, account_expired, account_locked, password_expired "
						+ "FROM users WHERE id = $1",
				Tuple.of(id)).map(rows -> {
					if (rows.rowCount() == 1) {
						User user = new User();
						for (Row row : rows.getDelegate()) {
							user.setId(row.getUUID("id"));
							user.setUsername(row.getString("username"));
							user.setEmail(row.getString("email"));
							user.setPassword("[MASKED]");
							user.setEnabled(row.getBoolean("enabled"));
							user.setAccountExpired(row.getBoolean("account_expired"));
							user.setAccountLocked(row.getBoolean("account_locked"));
							user.setPasswordExpired(row.getBoolean("password_expired"));
						}
						return user;
					} else
						throw new IncorrectResultSizeException(1, rows.rowCount());
				});
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

	@Override
	public Flowable<User> findAllUsers() {
		return client.rxBegin()
				.flatMapPublisher(tx -> tx
						.rxPrepare("SELECT id, username, email, password, "
								+ "enabled, account_expired, account_locked, password_expired FROM users")
						.flatMapPublisher(preparedQuery -> {
							PgStream<io.reactiverse.reactivex.pgclient.Row> stream = preparedQuery.createStream(50,
									Tuple.tuple());
							return stream.toFlowable().map(row -> {
								User user = new User();
								user.setId(row.getUUID("id"));
								user.setUsername(row.getString("username"));
								user.setEmail(row.getString("email"));
								user.setPassword("[MASKED]");
								user.setEnabled(row.getBoolean("enabled"));
								user.setAccountExpired(row.getBoolean("account_expired"));
								user.setAccountLocked(row.getBoolean("account_locked"));
								user.setPasswordExpired(row.getBoolean("password_expired"));
								return user;
							});
						})
						.doAfterTerminate(tx::commit));
	}

}
