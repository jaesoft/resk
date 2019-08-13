package io.resk.message.command.service;

import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.reactivex.Single;
import io.resk.message.command.domain.User;
import io.resk.message.command.repository.UserRepository;
import io.resk.message.command.vm.Account;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import static java.util.UUID.randomUUID;

@Singleton
@RequiredArgsConstructor
public class RegisterService {
    private final PasswordEncoder encoder;
    private final UserRepository repository;

    public Single<Account> register(Account user) {
        final var encodedPassword = encoder.encode(user.getPassword());
        var u = new User();
        u.setId(randomUUID());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(encodedPassword);

        return repository.save(u).map(Account::fromEntity);
    }
}
