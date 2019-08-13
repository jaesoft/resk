package io.resk.message.command.repository;

import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

import io.micronaut.context.annotation.Requires;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.resk.message.command.domain.Role;
import io.resk.message.command.domain.User;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@Singleton
@Requires(beans = PasswordEncoder.class)
@AllArgsConstructor
public class InMemoryUserRepository implements UserRepository {
	private static CopyOnWriteArraySet<User> users = new CopyOnWriteArraySet<>();
	private final PasswordEncoder passwordEncoder;

	@Override
	public void delete(@NonNull Serializable id) {
		users.removeIf(user -> id.equals(user.getId()));
	}

	@Override
	public Maybe<User> findById(@NotNull Serializable id) {
		return users.stream().filter(user -> id.equals(user.getId())) //
				.findFirst().map(Maybe::just) //
				.orElse(Maybe.empty());
	}

	@Override
	public Maybe<User> findByUsername(@NotNull String username) {
		return users.stream().filter(user -> username.equalsIgnoreCase(user.getUsername())) //
				.findFirst().map(Maybe::just) //
				.orElse(Maybe.empty());
	}

	@Override
	public Flowable<List<String>> findAllRolesByUsername(@NotNull String username) {
		return users.stream() //
				.filter(user -> username.equalsIgnoreCase(user.getUsername())) //
				.findFirst().map(user -> Flowable.just(user.getRoles())) //
				.orElse(Flowable.empty());
	}

	@Override
	public Flowable<User> findAll() {
		return Flowable.fromIterable(users);
	}

	// TODO check for conflict
	@Override
	public Single<User> save(User user) {
		users.add(user);
		return Single.just(user);
	}

	@Override
	public Single<User> save(String email, String username, String password) {
		var user = new User();
		user.setId(randomUUID());
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		return save(user);
	}

	@Override
	public Single<User> saveUserWithRole(User user, List<Role> roles) {
		var rolesString = roles.stream().map(Role::getAuthority).collect(toList());
		user.setId(randomUUID());
		user.setRoles(rolesString);
		users.add(user);
		return Single.just(user);
	}
}
