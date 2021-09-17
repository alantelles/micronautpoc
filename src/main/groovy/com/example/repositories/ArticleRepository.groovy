package com.example.repositories

import com.example.domain.Article

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


interface ArticleRepository {

        Optional<Article> findById(@NotNull Long id);

        Article save(@NotBlank String title, @NotBlank String body);

        Article saveWithException(@NotBlank String title, @NotBlank String body);

        void deleteById(@NotNull Long id);

        List<Article> findAll();

        int update(@NotNull Long id, @NotBlank String title, @NotBlank String body);

}
