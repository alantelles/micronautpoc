package com.example.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comments")
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    Post post;

    @NotNull
    @Column(name = "content", nullable = false)
    String content;

    String toString() {
        return "Comment: ${id}, from post: ${post.id}" as String
    }

}
