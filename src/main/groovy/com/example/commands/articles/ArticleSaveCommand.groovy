package com.example.commands.articles

import io.micronaut.core.annotation.Introspected

import javax.validation.constraints.NotBlank

@Introspected
class ArticleSaveCommand {

    @NotBlank
    String title;

    @NotBlank
    String content;

}
