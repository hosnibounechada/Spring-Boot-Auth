package com.hb.auth.model.postgres;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    @Id
    @SequenceGenerator(
            name = "review_sequence",
            sequenceName = "review_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "review_sequence"
    )
    private Integer id;
    private String title;
    private String comment;
    @ManyToOne
    private Book book;

    public Review(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Review review = (Review) o;
        return getId() != null && Objects.equals(getId(), review.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
