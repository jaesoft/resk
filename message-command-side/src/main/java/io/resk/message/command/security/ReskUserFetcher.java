package io.resk.message.command.security;

import io.micronaut.security.authentication.providers.UserFetcher;
import io.micronaut.security.authentication.providers.UserState;
import io.resk.message.command.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class ReskUserFetcher implements UserFetcher {
    private final UserRepository userRepository;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Publisher<UserState> findByUsername(String username) {
        return (Publisher) userRepository.findByUsername(username).toFlowable();
    }
}
