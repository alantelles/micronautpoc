package com.example.controllers

import com.example.annotations.Envelope
import com.example.commands.articles.ArticleSaveCommand
import com.example.commands.articles.ArticleUpdateCommand
import com.example.domain.Article
import com.example.repositories.ArticleRepository
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.inject.Inject
import jakarta.inject.Named

import javax.persistence.PersistenceException
import javax.validation.Valid

@ExecuteOn(TaskExecutors.IO)
@Controller("/articles")
class ArticlesController {


    final ArticleRepository articleRepository

    ArticlesController(@Named('scientific') ArticleRepository articleRepository) {
        this.articleRepository = articleRepository
    }

    @Get
    List<Article> index() {
        return articleRepository.findAll()
    }

    @Get("/{id}")
    @Envelope
    HttpResponse<Article> show(Long id) {
        Article article = articleRepository
                .findById(id)
                .orElse(null)
        return article ? HttpResponse.ok(article) : HttpResponse.notFound(article)
    }

    @Put("/{id}")
    HttpResponse update(@Body @Valid ArticleUpdateCommand command, Long id) {
        int affected = articleRepository.update(id, command.title, command.content)

        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(id).getPath())
                .header("X-updated", affected.toString())
    }

    @Post
    HttpResponse<Article> save(@Body @Valid ArticleSaveCommand command) {
        Article article = articleRepository.save(command.title, command.content)
        return HttpResponse.created(article).header('X-deu-certo', 'true')
    }

    @Post("/ex")
    HttpResponse<Article> saveExceptions(@Body @Valid ArticleSaveCommand command) {
        try {
            Article article = articleRepository.saveWithException(command.title, command.content)
            return HttpResponse.created(article)
                .header("x-deu-certo": 'true')
        } catch(PersistenceException e) {
            return HttpResponse.noContent()
        }
    }

    @Delete("/{id}")
    HttpResponse delete(Long id) {
        articleRepository.deleteById(id)
        return HttpResponse.noContent()
    }

    protected URI location(Long id) {
        return URI.create("/articles/" + id)
    }

    protected URI location(Article article) {
        return location(article.id)
    }
}
