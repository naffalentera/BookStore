package com.BookStore.BookStore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Document(collection = "book")
public class Book {

    @Id
    @Indexed(unique = true)
    private Long id;
    @Field(name = "name")    
    private String name;
    @Field(name = "author")
    private String author;
    @Field(name = "genre")
    private BookGenre bookGenre;
    @Field(name = "stock")
    private int stock;
    @Field(name = "price")
    private Double price;
    
    public Book(Long id, String name, String author, BookGenre bookGenre, int stock, Double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.bookGenre = bookGenre;
        this.stock = stock;
        this.price = price;
    }
    public Book() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookGenre getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(BookGenre bookGenre) {
        this.bookGenre = bookGenre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
