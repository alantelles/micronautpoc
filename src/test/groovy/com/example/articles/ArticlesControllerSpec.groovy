package com.example.articles

import com.example.commands.articles.ArticleSaveCommand;
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.spock.annotation.MicronautTest;
import jakarta.inject.Inject;
import spock.lang.Specification

@MicronautTest
class ArticlesControllerSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    void "trying to save valid post gets right"() {
        when:
        ArticleSaveCommand command = new ArticleSaveCommand(title: "This is America", content: "Not what you waiting on")
        HttpRequest request = HttpRequest.POST("/articles", command)
        HttpResponse response = client.toBlocking().exchange(request)

        then:
        response.status == HttpStatus.CREATED
    }

    void "non existent article returns 404"() {
        when:
        HttpStatus code
        HttpRequest request = HttpRequest.GET("/articles/1000")
        HttpResponse response = client.toBlocking().exchange(request)

        then:
        HttpClientResponseException e = thrown HttpClientResponseException
        e.status == HttpStatus.NOT_FOUND
    }

}
