package com.juliuskrah;

import java.util.UUID;

import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import reactor.core.publisher.Mono;

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

    @Override
    public Mono<HttpResponse<Status>> save(@Valid Status entity) {
        return super.save(entity);
    }

}