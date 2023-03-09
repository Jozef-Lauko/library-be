package sk.umb.example.library.borrowing.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import sk.umb.example.library.address.persistence.entity.AddressEntity;
import sk.umb.example.library.address.service.AddressDetailDto;
import sk.umb.example.library.book.service.BookDetailDTO;
import sk.umb.example.library.borrowing.persistance.entity.BorrowingEntity;
import sk.umb.example.library.borrowing.persistance.repository.BorrowingRepository;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;
import sk.umb.example.library.customer.persistence.repository.CustomerRepository;
import sk.umb.example.library.customer.service.CustomerDetailDTO;

/* TODO
    - pockat na BOOK
    - spojazdnit: chyby v update borrowing
 */


import java.util.*;

public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final CustomerRepository customerRepository;
    private final BookRepostirory bookRepostirory;

    public BorrowingService(BorrowingRepository borrowingRepository, CustomerRepository customerRepository, BookRepostirory bookRepostirory) {
        this.borrowingRepository = borrowingRepository;
        this.customerRepository = customerRepository;
        this.bookRepostirory = bookRepostirory;
    }

    public List<BorrowingDetailDTO> getAllBorrowings() {
        return mapToDtoList(borrowingRepository.findAll());
    }

    public BorrowingDetailDTO getBorrowingById(Long borrowingId) {
        return maptoDto(getBorrwingEntityById(borrowingId));
    }

    @Transactional
    public Long createBorrowing(BorrowingRequestDTO borrowingRequestDTO) {
        BorrowingEntity enitity = mapToEntity(borrowingRequestDTO);
        return borrowingRepository.save(enitity).getId();
    }

    @Transactional
    public void updateBorrowing(Long borrowingId, BorrowingRequestDTO borrowingRequestDTO) {
        BorrowingEntity entity = getBorrowingEntityByID(borrowingId);

        if(!Strings.isEmpty(borrowingRequestDTO.getCustomerId())){
            entity.setCustomerDetailDTO(borrowingRequestDTO.getCustomerId());
        }

        if(!Strings.isEmpty(borrowingRequestDTO.getBookId())){
            entity.setBookDetailDTO(borrowingRequestDTO.getBookId());
        }

        entity.setDate(new Date());
    }

    @Transactional
    public void deleteBorrowing(Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
    }

    private BorrowingEntity getBorrowingEntityByID(Long borrowingId) {
        Optional<BorrowingEntity>entity = borrowingRepository.findById(borrowingId);

        if(entity.isEmpty()) {
            throw new IllegalArgumentException("Borrowing not found. ID: " + borrowingId);
        }

        return entity.get();
    }


    private BorrowingEntity mapToEntity(BorrowingRequestDTO dto) {
        BorrowingEntity entity = new BorrowingEntity();

        if(!Objects.isNull(dto.getCustomerId()) ){
            Optional<CustomerEntity>customer = customerRepository.findById()(dto.getCustomerId());

            if(customer.isPresent()){
                entity.setCustomer(customer.get());
            }
        }

        if(!Objects.isNull(dto.getBookId()) ){
            Optional<BookEntity>book = bookRepository.findById()(dto.getBookId());

            if(book.isPresent()){
                entity.setBookEntity(book.get());
            }
        }

        return entity;
    }

    private List<BorrowingDetailDTO> mapToDtoList(Iterable<BorrowingEntity> borrowingEntities) {
        List<BorrowingDetailDTO> borrowings = new ArrayList<>();

        borrowingEntities.forEach(borrowingEntity -> {
            BorrowingDetailDTO dto = maptoDto(borrowingEntity);
            borrowings.add(dto);
        });
    }

    private BorrowingDetailDTO maptoDto(BorrowingEntity borrowingEntity){
        BorrowingDetailDTO dto = new BorrowingDetailDTO();
        dto.setId(borrowingEntity.getId());
        dto.setCustomerDetailDTO(mapToDto(borrowingEntity.getCustomer()));
        dto.setBookDetailDTO(mapToDto(borrowingEntity.getBookEntity()));
        dto.setDate(new Date());

        return dto;
    }

    private CustomerDetailDTO mapToDto(CustomerEntity customer) {
        CustomerDetailDTO dto = new CustomerDetailDTO();

        dto.setId(customer.getId());
        dto.setAddress(mapToDto(customer.getAddress()));
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmailContact(customer.getEmailContact());

        return dto;
    }

    private AddressDetailDto mapToDto(AddressEntity addressEntity) {
        AddressDetailDto dto = new AddressDetailDto();

        dto.setId(addressEntity.getId());
        dto.setCity(addressEntity.getCity());

        return dto;
    }

    private BookDetailDTO mapToDto(BookEntity book) {
        BookDetailDTO dto = new BookDetailDTO();

//        ****************TODO****************

    }

}
