package sk.umb.example.library.borrowing.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import sk.umb.example.library.book.persistance.entity.BookEntity;
import sk.umb.example.library.book.service.BookDetailDTO;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;
import sk.umb.example.library.customer.service.CustomerDetailDTO;

import java.util.Date;

@Entity(name = "borrowing")
public class BorrowingEntity {
    @Id
    @GeneratedValue
    private Long id;
    private CustomerDetailDTO customerDetailDTO;
    private BookDetailDTO bookDetailDTO;
    private Date date;

    @ManyToOne
    private CustomerEntity customer;

    @ManyToOne
    private BookEntity bookEntity;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDetailDTO getCustomerDetailDTO() {
        return customerDetailDTO;
    }

    public void setCustomerDetailDTO(CustomerDetailDTO customerDetailDTO) {
        this.customerDetailDTO = customerDetailDTO;
    }

    public BookDetailDTO getBookDetailDTO() {
        return bookDetailDTO;
    }

    public void setBookDetailDTO(BookDetailDTO bookDetailDTO) {
        this.bookDetailDTO = bookDetailDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
