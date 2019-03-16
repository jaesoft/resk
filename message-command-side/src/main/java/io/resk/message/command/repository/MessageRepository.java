package io.resk.message.command.repository;

import io.reactivex.Single;
import io.resk.message.command.vm.Message;

public interface MessageRepository {
	Single<Message> createMessage(Message message);

	Single<Message> updateMessage(Message message, String messageId);
}
