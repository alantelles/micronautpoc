package com.example.posts;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.spock.annotation.MicronautTest;
import jakarta.inject.Inject;
import spock.lang.Specification

@MicronautTest
class PostsControllerSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    void "test posts index"() {
        when:
        HttpRequest request = HttpRequest.GET("/posts")
        String rsp = client.toBlocking().retrieve(request)

        then:
        rsp == '{"title":"This is America","body":"This is America. Look waht you wainting on"}'
    }

}
