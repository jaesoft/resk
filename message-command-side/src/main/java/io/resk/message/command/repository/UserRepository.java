package io.resk.message.command.repository;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.resk.message.command.domain.Role;
import io.resk.message.command.domain.User;

public interface UserRepository {

	void delete(Serializable id);

	Single<User> findById(Serializable id);

	Single<User> findByUsername(String username);

	Flowable<String> findAllRolesByUsername(String username);

	Flowable<User> findAllUsers();

	Single<User> save(User user);

	Single<User> save(String email, String username, String password);

	Single<User> saveUserWithRole(User user, List<Role> roles);

}
