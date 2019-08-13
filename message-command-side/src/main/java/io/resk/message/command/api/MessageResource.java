package io.resk.message.command.api;

import static java.util.Optional.ofNullable;
import static io.micronaut.http.HttpStatus.*;

import java.security.Principal;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.Valid;

import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.reactivex.Maybe;
import io.resk.message.command.repository.ProjectRepository;
import io.resk.message.command.vm.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Secured("${resk.routes.secured}")
@Controller("/projects")
@AllArgsConstructor
public class MessageResource {
	private final ProjectRepository projectRepository;

	// https://firebase.google.com/docs/cloud-messaging/send-message
	// https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages/send
	@Version("1")
	@Post("{projectId}/messages:send")
	public Maybe<HttpStatus> send(UUID projectId, @Body @Valid Message message, //
			@Nullable Principal principal) {
		return ofNullable(principal)
				// Authentication enabled
				.map(user -> {
					log.info("Authenticated route, Principal: {}, Project: {}, Payload: {}", //
							user.getName(), projectId, message);
					// Check project-id belongs to user
					return projectRepository.find(user.getName(), projectId).map(project -> {
						// Found the project - Do something with message
						// Cache result to be faster next time
						return ACCEPTED;
					});

					// 404 if project does not exist for user
					// Message: unknown project-id
					// .defaultIfEmpty(HttpStatus.NOT_FOUND);
				})
				// Authentication disabled
				.orElseGet(() -> {
					log.info("Unauthenticated route, Project: {}, Payload: {}", projectId, message);
					// Check project-id exists
					return projectRepository.find(projectId).map(project -> {
						// Found the project - Do something with payload
						// Cache result to be faster next time
						return ACCEPTED;
					});
				});
	}

	@Version("2")
	@Post("{projectId}/messages:send")
	public HttpStatus sendV2(String projectId, @Body Message message, @Nullable Principal principal) {
		return HttpStatus.NOT_IMPLEMENTED;
	}
}
