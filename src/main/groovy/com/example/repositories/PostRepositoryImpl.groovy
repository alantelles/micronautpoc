package com.example.repositories

import com.example.configs.ApplicationConfiguration
import io.micronaut.http.annotation.Post
import io.micronaut.transaction.annotation.ReadOnly

import javax.persistence.EntityManager
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class PostRepositoryImpl implements PostRepository{
    final EntityManager entityManager;
    final ApplicationConfiguration applicationConfiguration;

    List<String> VALID_PROPERTY_NAMES = ["id", "title", "body"]

    @Override
    @ReadOnly
    Optional<Post> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(Post.class, id))
    }

    Post save(@NotBlank String title, @NotBlank String body) {
        Post p = new Post(title: title, body: body)
    }
}
