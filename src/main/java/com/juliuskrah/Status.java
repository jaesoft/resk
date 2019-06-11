package com.juliuskrah;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Status.StatusBuilder.class)
public class Status {
    private UUID id;
    @Size(max = 100)
    @NotBlank
    private String name;
    private boolean editable;
    private String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StatusBuilder {}
}