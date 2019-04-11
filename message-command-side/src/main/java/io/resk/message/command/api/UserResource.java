package io.resk.message.command.api;

import java.util.UUID;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.resk.message.command.domain.User;
import io.resk.message.command.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@Secured("${resk.routes.secured}")
public class UserResource {
	private final UserRepository userRepository;

	@Post("/users")
	public Single<User> createUser(@Body User user) {
		return userRepository.save(user);
	}

	@Get("/users")
	public Flowable<User> retrieveUsers() {
		return userRepository.findAllUsers();
	}

	@Get("/users/{username}/username")
	public Single<User> retrieveUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Get("/users/{id}")
	public Single<User> retrieveUser(UUID id) {
		return userRepository.findById(id);
	}
}
