package com.example.commands.articles

import io.micronaut.core.annotation.Introspected

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
class ArticleUpdateCommand {
    @NotNull
    Long id;

    @NotBlank
    String title

    @NotBlank
    String body
}
