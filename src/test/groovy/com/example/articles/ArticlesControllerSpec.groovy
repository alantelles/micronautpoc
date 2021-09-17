package com.example.articles

import com.example.commands.articles.ArticleSaveCommand;
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.spock.annotation.MicronautTest;
import jakarta.inject.Inject;
import spock.lang.Specification

@MicronautTest
class ArticlesControllerSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    void "trying to save invalid post fails"() {
        when:
        ArticleSaveCommand command = new ArticleSaveCommand(title: "This is America", body: "Not what you waiting on")
        HttpRequest request = HttpRequest.POST("/articles", command)
        HttpResponse response = client.toBlocking().exchange(request)

        then:
        response.status == HttpResponse.CREATED
    }

}
