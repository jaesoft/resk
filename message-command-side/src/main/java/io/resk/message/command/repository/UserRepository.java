package io.resk.message.command.repository;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.resk.message.command.domain.Role;
import io.resk.message.command.domain.User;

public interface UserRepository {

	void delete(Serializable id);

	Maybe<User> findById(Serializable id);

	Maybe<User> findByUsername(String username);

	Flowable<List<String>> findAllRolesByUsername(String username);

	Flowable<User> findAll();

	Single<User> save(User user);

	Single<User> save(String email, String username, String password);

	Single<User> saveUserWithRole(User user, List<Role> roles);

}
