package io.resk.message.command.vm;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Message.MessageBuilder.class)
public class Message {
	// @NotBlank
	// String name;
	Map<String, Object> data;
	Notification notification;
	Object android;
	Object webpush;
	Object apns;
	// Union field target can be only one of the following:
	String token;
	String topic;
	String condition;
	// End of list of possible types for union field target.
	
	@JsonPOJOBuilder(withPrefix = "")
    public static class MessageBuilder {

    }
}
