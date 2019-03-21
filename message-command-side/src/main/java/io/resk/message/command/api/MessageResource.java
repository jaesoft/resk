package io.resk.message.command.api;

import java.security.Principal;

import javax.annotation.Nullable;

import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.resk.message.command.vm.Message;
import lombok.extern.slf4j.Slf4j;

// https://firebase.google.com/docs/cloud-messaging/send-message
// https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages/send
@Slf4j
@Secured("${resk.routes.secured}")
@Controller("/projects")
public class MessageResource {

	@Version("1")
	@Post("{projectId}/messages:send")
	public HttpStatus send(String projectId, @Body Message message, @Nullable Principal principal) {
		log.info("Principal: {}, Project v1: {}, Payload: {}", principal, projectId, message);
		return HttpStatus.ACCEPTED;
	}

	@Version("2")
	@Post("{projectId}/messages:send")
	public HttpStatus sendV2(String projectId, @Body Message message, @Nullable Principal principal) {
		return HttpStatus.NOT_IMPLEMENTED;
	}
}
