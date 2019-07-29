package io.resk.message.command.api;

import java.util.UUID;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.validation.Validated;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.resk.message.command.domain.User;
import io.resk.message.command.repository.UserRepository;
import io.resk.message.command.service.RegisterService;
import io.resk.message.command.vm.Account;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@Validated
@Controller("/users")
@Secured("${resk.routes.secured}")
@RequiredArgsConstructor
@Requires(property = "resk.features.security-mode", value = "embedded")
public class UserResource {
	private final UserRepository userRepository;
	private final RegisterService registerService;

	@Post
	public Single<Account> createUser(@Valid @Body Account user) {
		return registerService.register(user);
	}

	@Get
	public Flowable<User> retrieveUsers() {
		return userRepository.findAll();
	}

	@Get("/{username}/username")
	public Maybe<User> retrieveUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Get("/{id}")
	public Maybe<User> retrieveUser(UUID id) {
		return userRepository.findById(id);
	}

	@Status(HttpStatus.NO_CONTENT)
	@Delete("/{id}")
	public void deleteUser(UUID id) {
		userRepository.delete(id);
	}
}
