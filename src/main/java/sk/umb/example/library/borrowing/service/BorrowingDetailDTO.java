package sk.umb.example.library.borrowing.service;

import sk.umb.example.library.customer.service.CustomerDetailDTO;

import java.util.Date;

public class BorrowingDetailDTO {
    private Long id;
    private CustomerDetailDTO customerDetailDTO;
//    private BookDetailDTO bookDetailDTO;
//    Date of borrowing
    private Date date;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
