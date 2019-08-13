package io.resk.message.command.vm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import io.resk.message.command.domain.User;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = Account.AccountBuilder.class)
public class Account {
    private UUID id;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    
    public static Account fromEntity(User user) {
    	return Account.builder() //
    			.id(user.getId()) //
    			.username(user.getUsername()) //
    			.email(user.getEmail()) //
    			.password("[REDACTED]")
    			.build();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountBuilder {}
}
