package sk.umb.example.library.borrowing.service;

import sk.umb.example.library.book.service.BookDetailDTO;
import sk.umb.example.library.customer.service.CustomerDetailDTO;

public class BorrowingRequestDTO {
    private CustomerDetailDTO customerId;
    private BookDetailDTO bookId;


    public CustomerDetailDTO getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerDetailDTO customerId) {
        this.customerId = customerId;
    }

    public BookDetailDTO getBookId() {
        return bookId;
    }

    public void setBookId(BookDetailDTO bookId) {
        this.bookId = bookId;
    }
}
