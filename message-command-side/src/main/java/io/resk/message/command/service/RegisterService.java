package io.resk.message.command.service;

import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.reactivex.Single;
import io.resk.message.command.domain.User;
import io.resk.message.command.repository.UserRepository;
import io.resk.message.command.vm.Account;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Singleton
@RequiredArgsConstructor
public class RegisterService {
    private final PasswordEncoder encoder;
    private final UserRepository repository;

    public Single<Account> register(Account user) {
        // TODO check for duplicate
        final var encodedPassword = encoder.encode(user.getPassword());
        user = Account.builder() //
                .id(randomUUID()) //
                .username(user.getUsername()) //
                .email(user.getEmail()) //
                .password(null)
                .build();
        var u = new User();
        u.setId(randomUUID());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(encodedPassword);

        repository.save(u);
        return Single.just(user);
    }
}
