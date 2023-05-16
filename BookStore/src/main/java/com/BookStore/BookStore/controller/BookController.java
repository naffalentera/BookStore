package com.BookStore.BookStore.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import com.BookStore.BookStore.controller.exception.BookIdAlreadyExistsException;
import com.BookStore.BookStore.model.Book;
import com.BookStore.BookStore.repository.BookRepository;

import reactor.core.publisher.Mono;


@Controller
public class BookController {

    @Autowired
    private BookRepository bRepo;

    @GetMapping("/")
	public String home() {
		return "home";
	}

    @GetMapping("/allbooks")
    public String getAllBook(final Model model){
        IReactiveDataDriverContextVariable reactiveDataDriverContextVariable
            = new ReactiveDataDriverContextVariable(bRepo.findAll());
        model.addAttribute("books",reactiveDataDriverContextVariable);
        return "AllBooks";
    }

    @GetMapping(path = {"/edit/{id}"})
    public String updateBookById(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Long bookId = id.get();
            Mono<Book> book = bRepo.findById(bookId);
            model.addAttribute("book", book);
        } 
        return "editBook";
    }

    @GetMapping(path = {"/add"})
    public String createBookById(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Long bookId = id.get();
            Mono<Book> book = bRepo.findById(bookId);

            if (book.blockOptional().isPresent()) {
                throw new BookIdAlreadyExistsException("Book with ID " + bookId + " already exists");
            }
            model.addAttribute("errorMessage", "");
        } else {
            model.addAttribute("book", new Book());
        }    

        return "addBook";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(final Model model, @PathVariable Long id){
        bRepo.deleteById(id).subscribe();
        return "redirect:/allbooks";
    }
    
    @PostMapping("/create")
    public String createBook(Book book){
        bRepo.save(book).subscribe();
        return "redirect:/allbooks";
    }

    @GetMapping("/{name}")
    public String getBookByName(Model model, @PathVariable String name){
        if(name.isBlank()){
            StringFormattedMessage("Nama buku tidak ada");
        }else{
            IReactiveDataDriverContextVariable reactiveDataDriverContextVariable
            = new ReactiveDataDriverContextVariable(bRepo.findById(name));
        model.addAttribute("books",reactiveDataDriverContextVariable);
        }
        return "redirect:/allbooks";
    }

    private void StringFormattedMessage(String string) {
    }

}
