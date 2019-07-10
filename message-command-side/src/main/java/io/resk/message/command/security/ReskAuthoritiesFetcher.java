package io.resk.message.command.security;

import io.micronaut.security.authentication.providers.AuthoritiesFetcher;
import io.resk.message.command.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class ReskAuthoritiesFetcher implements AuthoritiesFetcher {
    private final UserRepository userRepository;

    @Override
    public Publisher<List<String>> findAuthoritiesByUsername(String username) {
        return userRepository.findAllRolesByUsername(username);
    }
}
