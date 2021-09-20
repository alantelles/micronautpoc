package com.example.repositories

import com.example.configs.ApplicationConfiguration
import com.example.domain.Article
import io.micronaut.transaction.annotation.ReadOnly
import jakarta.inject.Named
import jakarta.inject.Singleton

import javax.persistence.EntityManager
import javax.persistence.PersistenceException
import javax.persistence.TypedQuery
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Named("scientific")
@Singleton
class ScientificArticleRepository implements ArticleRepository{
    final EntityManager entityManager;
    final ApplicationConfiguration applicationConfiguration;

    List<String> VALID_PROPERTY_NAMES = ["id", "title", "content"]

    ScientificArticleRepository(EntityManager entityManager, ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @ReadOnly
    Optional<Article> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(Article.class, id))
    }

    @Override
    @Transactional
    Article save(@NotBlank String title, @NotBlank String content) {
        title = "This is a scientific article: " + title
        content = "Published at now:\n" + content
        Article post = new Article(title: title, content: content)
        entityManager.persist(post)
        return post
    }

    @Override
    @Transactional
    void deleteById(@NotNull Long id) {
        findById(id).ifPresent(it -> entityManager.remove(it))
    }

    @ReadOnly
    List<Article> findAll() {
        String qlString = 'SELECT p FROM Article as p'
        TypedQuery<Article> query = entityManager.createQuery(qlString, Article.class)
        query.setMaxResults(applicationConfiguration.max)
        return query.getResultList()
    }

    @Override
    @Transactional
    int update(@NotNull Long id, @NotBlank String title, @NotBlank String content) {
        String qStr = "UPDATE Article p SET title = :title, content = :content WHERE id = :id"
        return entityManager.createQuery(qStr)
                .setParameter('content', content)
                .setParameter('title', title)
                .setParameter('id', id)
                .executeUpdate()
    }

    @Override
    @Transactional
    Article saveWithException(@NotBlank String title, @NotBlank String content) {
        save(title, content)
        throw new PersistenceException()
    }
}
