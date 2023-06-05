package com.hb.auth.controller;

import com.hb.auth.exception.NotFoundException;
import com.hb.auth.model.postgres.Book;
import com.hb.auth.payload.request.book.BookInput;
import com.hb.auth.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @QueryMapping
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book findBook(@Argument Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument Integer pages, @Argument String author) {
        Book book = new Book(title, pages, author);
        return bookRepository.save(book);
    }

    @MutationMapping
    public Book addBook(@Argument BookInput book) {
        return bookRepository.save(new Book(book.title(), book.pages(), book.author()));
    }

    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument BookInput book) {
        Book bookToUpdate = bookRepository.findById(id).orElse(null);
        if (bookToUpdate == null) {
            throw new NotFoundException("Book not found");
        }
        bookToUpdate.setTitle(book.title());
        bookToUpdate.setPages(book.pages());
        bookToUpdate.setAuthor(book.author());
        bookRepository.save(bookToUpdate);
        return bookToUpdate;
    }

    @MutationMapping
    public void deleteBook(@Argument Long id) {
        bookRepository.deleteById(id);
    }

}
