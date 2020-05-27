package ru.itis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.dto.BookForm;
import ru.itis.model.Author;
import ru.itis.model.Book;
import ru.itis.service.AuthorService;
import ru.itis.service.BookService;

import javax.validation.Valid;

@Controller
public class BooksController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @PreAuthorize("permitAll()")
    @GetMapping("/books")
    public String getPage(Model model) {
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("bookForm", new BookForm());
        return "books";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/books")
    public String addBook(@RequestParam String authorFirstName, @RequestParam String authorLastName,
                          @RequestParam String name, @RequestParam String text, @Valid BookForm form,
                          Model model) {
        System.out.println(form);
        Author author = authorService.findByName(authorFirstName, authorLastName);
        if (author == null) {
            authorService.save(Author.builder()
                    .firstName(authorFirstName)
                    .lastName(authorLastName).build());
            author = authorService.findByName(authorFirstName, authorLastName);
        }
        bookService.save(Book.builder().text(text)
                .name(name)
                .author(author)
                .build());
        model.addAttribute("postForm", form);
        return "redirect:/books";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/book")
    public String getBook(@RequestParam Long bookId, Model model) {
        model.addAttribute("book", bookService.find(bookId));
        return "book";
    }


}
