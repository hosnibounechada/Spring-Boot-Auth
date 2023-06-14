package com.hb.auth.model.postgres;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Integer id;
    private String title;
    private Integer pages;
    private String author;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Review> reviews;

    public Book(String title, Integer pages, String author) {
        this.title = title;
        this.pages = pages;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return getId() != null && Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}