package com.hb.auth.model.postgres;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private Integer pages;
    private String author;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    public Book(String title, Integer pages, String author) {
        this.title = title;
        this.pages = pages;
        this.author = author;
    }
}