package io.resk.message.command.vm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
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

    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountBuilder {}
}
