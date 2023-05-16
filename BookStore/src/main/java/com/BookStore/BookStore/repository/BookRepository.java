package com.BookStore.BookStore.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.BookStore.BookStore.model.Book;

import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, Long> {

    Mono<Book> findById(String string);
    
}
