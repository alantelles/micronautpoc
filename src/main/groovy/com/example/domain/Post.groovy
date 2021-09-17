package com.example.domain

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "posts")
class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    String title;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    List<Comment> comments = new Comment();

    @NotNull
    @Column(name = "content", nullable = false)
    String content;

    String toString() {
        return "Post: ${id}, title: ${title}" as String
    }

}
