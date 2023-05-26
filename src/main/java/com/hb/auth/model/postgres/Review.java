package com.hb.auth.model.postgres;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Review {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String comment;
    @ManyToOne
    private Book book;

    public Review(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }
}
