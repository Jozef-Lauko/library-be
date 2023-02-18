package sk.umb.example.library.borrowing.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class BorrowingController {
    @GetMapping("/api/borrowings")
    public List searchBorrowings(@RequestParam(required = false) String lastName) {
        System.out.println("Search borrowing called.");

        return Collections.emptyList();
    }

    @GetMapping("/api/borrowings/{borrowingId}")
    public void getBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Get borrowing called: ID = " + borrowingId);
    }

    @PostMapping("/api/borrowings/{borrowingId}")
    public void createBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Create borrowing called: ID = " + borrowingId);
    }

    @PutMapping("/api/borrowings/{borrowingId}")
    public void updateBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Update borrowing called: ID = " + borrowingId);
    }

    @DeleteMapping ("/api/borrowings/{borrowingId}")
    public void deleteBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Delete borrowing called: ID = " + borrowingId);
    }
}
