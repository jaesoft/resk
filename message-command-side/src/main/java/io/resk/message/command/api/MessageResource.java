package io.resk.message.command.api;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.resk.message.command.vm.Message;
import lombok.extern.slf4j.Slf4j;

// https://fcm.googleapis.com/v1/{parent=projects/*}/messages:send
// https://firebase.google.com/docs/cloud-messaging/send-message
// https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages/send
@Slf4j
@Controller("/v1/projects")
public class MessageResource {

	@Post("{projectId}/messages:send")
	public HttpStatus send(String projectId, @Body Message message) {
		log.info("Project: {}, Payload: {}", projectId, message);
		return HttpStatus.ACCEPTED;
	}

}
