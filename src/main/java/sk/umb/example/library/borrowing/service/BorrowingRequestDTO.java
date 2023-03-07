package sk.umb.example.library.borrowing.service;

import sk.umb.example.library.customer.service.CustomerDetailDTO;

public class BorrowingRequestDTO {
    private Long customerId;
    private Long bookId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
