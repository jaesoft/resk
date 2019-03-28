package io.resk.message.command.repository;

import java.io.Serializable;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.resk.message.command.domain.Role;
import io.resk.message.command.domain.User;

public interface UserRepository {
	Single<User> save(User user);

	Single<User> save(String email, String username, String password);

	Single<User> findByUsername(String username);

	Single<User> findById(Serializable id);

	void delete(Serializable id);

	Single<User> saveUserWithRole(User user, Role role);

	Flowable<String> findAllRolesByUsername(String username);
}
