package sk.umb.example.library.borrowing.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.borrowing.service.BorrowingDetailDTO;
import sk.umb.example.library.borrowing.service.BorrowingRequestDTO;
import sk.umb.example.library.borrowing.service.BorrowingService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BorrowingController {
    private final BorrowingService borrowingService = new BorrowingService();

    @GetMapping("/api/borrowings")
    public List<BorrowingDetailDTO> searchBorrowings() {
        System.out.println("Search borrowing called.");

        return borrowingService.getAllBorrowings();
    }

    @GetMapping("/api/borrowings/{borrowingId}")
    public BorrowingDetailDTO getBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Get borrowing called: ID = " + borrowingId);

        return borrowingService.getBorrowingById(borrowingId);
    }

    @PostMapping("/api/borrowings")
    public Long createBorrowing(@RequestBody BorrowingRequestDTO borrowingRequestDTO) {
        System.out.println("Create borrowing called:");

        return borrowingService.createBorrowing(borrowingRequestDTO);
    }
//
//    @PutMapping("/api/borrowings/{borrowingId}")
//    public void updateBorrowing(@PathVariable Long borrowingId, @RequestBody BorrowingRequestDTO borrowingRequestDTO) {
//        System.out.println("Update borrowing called: ID");
//
//        borrowingService.updateBorrowing(borrowingId, borrowingRequestDTO);
//    }
//
//    @DeleteMapping ("/api/borrowings/{borrowingId}")
//    public void deleteBorrowing(@PathVariable Long borrowingId) {
//        System.out.println("Delete borrowing called: ID = " + borrowingId);
//    }
}

