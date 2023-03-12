package sk.umb.example.library.book.controller;

import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.book.service.BookDetailDTO;
import sk.umb.example.library.book.service.BookRequestDTO;
import sk.umb.example.library.book.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/api/books")
    public BookDetailDTO createBook(@RequestBody BookRequestDTO bookRequest) {
        System.out.println("Create book called:");
        return bookService.createBook(bookRequest);
    }

    @GetMapping("/api/books/{bookId}")
    public BookDetailDTO getBook(@PathVariable Long bookId) {
        System.out.println("Get book called: ID = " + bookId);
        return bookService.getBook(bookId);
    }

    @GetMapping("/api/books")
    public List<BookDetailDTO> getAllBooks() {
        System.out.println("Get all books:");
        return bookService.getAllBooks();
    }

    @PutMapping("/api/books/{bookId}")
    public BookDetailDTO updateBook(@PathVariable Long bookId,
                                    @RequestBody BookRequestDTO bookRequest) {
        System.out.println("Update book called: ID = " + bookId);
        return bookService.updateBook(bookId, bookRequest);
    }

    @DeleteMapping("/api/books/{bookId}")
    public boolean deleteBook(@PathVariable Long bookId) {
        System.out.println("Delete book called: ID = " + bookId);
        return bookService.deleteBook(bookId);
    }
}