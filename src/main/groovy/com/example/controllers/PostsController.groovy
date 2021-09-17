package com.example.controllers
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/posts")
class PostsController {

    @Get(produces = MediaType.APPLICATION_JSON)
    public Map index() {
        return [title: "This is America", body: "This is America. Look waht you wainting on"]
    }
}
