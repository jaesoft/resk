package io.resk.message.command.repository;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.resk.message.command.domain.Role;
import io.resk.message.command.domain.User;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;
import static java.util.UUID.fromString;

@Slf4j
@Singleton
public class InMemoryUserRepository implements UserRepository {
    private static CopyOnWriteArraySet<User> users = new CopyOnWriteArraySet<>();

    static {
        var user = new User();
        user.setId(fromString("2c9de2b9-d9e1-40b4-b0b7-2d60900a7a38"));
        user.setEmail("admin@resk.io");
        user.setPassword("$2a$10$rfjZ0l/Q78mXOwU9mfczZuYF7kEPqodx9ZI.tLRUmIa65WTmMfB9e");
        user.setUsername("admin");
        users.add(user);
        log.info("User added");
    }

    @Override
    public void delete(@NonNull Serializable id) {
        users.removeIf(user -> id.equals(user.getId()));
    }

    @Override
    public Maybe<User> findById(@NotNull Serializable id) {
        return users.stream().filter(user -> id.equals(user.getId()))
                .findFirst()
                .map(Maybe::just)
                .orElse(Maybe.empty());
    }

    @Override
    public Maybe<User> findByUsername(@NotNull String username) {
        return users.stream().filter(user -> username.equalsIgnoreCase(user.getUsername()))
                .findFirst()
                .map(Maybe::just)
                .orElse(Maybe.empty());
    }

    @Override
    public Flowable<List<String>> findAllRolesByUsername(@NotNull String username) {
        return users.stream() //
        		.filter(user -> username.equalsIgnoreCase(user.getUsername()))
                .findFirst()
                .map(user -> Flowable.just(user.getRoles()))
                .orElse(Flowable.empty());
    }

    @Override
    public Flowable<User> findAll() {
        return Flowable.fromIterable(users);
    }

    // TODO check for conflict
    @Override
    public Single<User> save(User user) {
        users.add(user);
        return Single.just(user);
    }

    // TODO check for conflict
    @Override
    public Single<User> save(String email, String username, String password) {
        var user = new User();
        user.setId(randomUUID());
        user.setEmail(email);
        // encrypt password
        user.setPassword(password);
        users.add(user);
        return Single.just(user);
    }

    @Override
    public Single<User> saveUserWithRole(User user, List<Role> roles) {
        var rolesString = roles.stream().map(Role::getAuthority).collect(Collectors.toList());
        user.setId(randomUUID());
        user.setRoles(rolesString);
        users.add(user);
        return Single.just(user);
    }
}
