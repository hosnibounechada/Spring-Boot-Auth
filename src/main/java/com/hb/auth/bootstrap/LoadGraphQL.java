package com.hb.auth.bootstrap;

import com.hb.auth.model.postgres.Book;
import com.hb.auth.model.postgres.Review;
import com.hb.auth.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@RequiredArgsConstructor
public class LoadGraphQL implements CommandLineRunner {
    private final BookRepository bookRepository;
    @Override
    public void run(String... args) throws Exception {
        Book reactiveSpring = new Book("Reactive Spring", 484, "Josh Long");
        Review review = new Review("Great book", "I really enjoyed this book");
        // bidirectional relationship
        review.setBook(reactiveSpring);
        reactiveSpring.setReviews(List.of(review));
        bookRepository.save(reactiveSpring);

        bookRepository.save(new Book("Spring Boot Up & Running", 328, "Mark Heckler"));
        bookRepository.save(new Book("Hacking with Spring Boot 2.3", 392, "Greg Turnquist"));
    }
}
