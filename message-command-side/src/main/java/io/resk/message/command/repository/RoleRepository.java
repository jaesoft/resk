package io.resk.message.command.repository;

import io.resk.message.command.domain.Role;

public interface RoleRepository {
	Role save(Role role);

	Role save(String authority);

	Role find(String authority);

	void delete(String authority);
}
