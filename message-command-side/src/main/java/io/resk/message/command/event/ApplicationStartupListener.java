package io.resk.message.command.event;

import static java.util.UUID.fromString;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import io.resk.message.command.domain.User;
import io.resk.message.command.repository.UserRepository;
import lombok.AllArgsConstructor;

@Singleton
@Requires(property = "resk.features.storage", value = "memory")
@AllArgsConstructor
public class ApplicationStartupListener implements ApplicationEventListener<StartupEvent> {
	private final UserRepository repository;

	@Override
	public void onApplicationEvent(StartupEvent event) {
		var user = new User();
        user.setId(fromString("2c9de2b9-d9e1-40b4-b0b7-2d60900a7a38"));
        user.setEmail("admin@resk.io");
        user.setPassword("$2a$10$rfjZ0l/Q78mXOwU9mfczZuYF7kEPqodx9ZI.tLRUmIa65WTmMfB9e");
        user.setUsername("admin");
        this.repository.save(user).subscribe();
	}

}
