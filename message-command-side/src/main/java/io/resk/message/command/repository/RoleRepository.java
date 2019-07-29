package io.resk.message.command.repository;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.resk.message.command.domain.Role;

public interface RoleRepository {
	Single<Role> save(Role role);

	Single<Role> save(String authority);

	Maybe<Role> find(String authority);

	void delete(String authority);
}
