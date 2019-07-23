package com.juliuskrah;

import java.util.UUID;

import io.micronaut.http.annotation.Controller;

@Controller("/statuses")
public class StatusController extends GenericController<Status, UUID> {
    public StatusController(StatusService<Status, UUID> service) {
        super(service);
    }
}