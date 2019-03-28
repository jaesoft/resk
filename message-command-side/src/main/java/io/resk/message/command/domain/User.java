package io.resk.message.command.domain;

import java.util.UUID;

import io.micronaut.security.authentication.providers.UserState;
import lombok.Data;

@Data
public class User implements UserState {
	private UUID id;
	private String username;
	private String email;
	private String password;
	private boolean enabled = true;
	private boolean accountExpired = false;
	private boolean accountLocked = false;
	private boolean passwordExpired = false;
}
