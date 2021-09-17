package com.example.repositories

import com.example.domain.Post

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull;


interface PostRepository {



    interface PostRepository {

        Optional<Post> findById(@NotNull Long id);

        Post save(@NotBlank String title, @NotBlank String body);

        Genre saveWithException(@NotBlank String title, @NotBlank String body);

        void deleteById(@NotNull Long id);

        List<Post> findAll();

        int update(@NotNull Long id, @NotBlank String title, @NotBlank String body);
    }
}
