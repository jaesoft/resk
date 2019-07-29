package io.resk.message.command.domain;

import java.util.UUID;

import lombok.Data;

@Data
public class Project {
	private UUID id;
	private String name;
	private String description;
	private String owner;
}
