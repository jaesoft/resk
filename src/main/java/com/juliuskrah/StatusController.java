package com.juliuskrah;

import java.util.UUID;

import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;

@Validated
@Controller(StatusController.PATH)
public class StatusController extends GenericController<Status, UUID> {
    static final String PATH = "/statuses";
    public StatusController(StatusService<Status> service) {
        super(service);
    }
    @Override
    public String getPath() {
        return PATH;
    }

}