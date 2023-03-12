package sk.umb.example.library.borrowing.service;

import jakarta.transaction.Transactional;
import sk.umb.example.library.book.persistance.enity.BookEntity;
import sk.umb.example.library.book.persistance.repository.BookRepository;
import sk.umb.example.library.borrowing.persistance.entity.BorrowingEntity;
import sk.umb.example.library.borrowing.persistance.repository.BorrowingRepository;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;
import sk.umb.example.library.customer.persistence.repository.CustomerRepository;
import java.util.*;

public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    public BorrowingService(BorrowingRepository borrowingRepository, CustomerRepository customerRepository, BookRepository bookRepository) {
        this.borrowingRepository = borrowingRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    public List<BorrowingDetailDTO> getAllBorrowings() {
        return mapToDtoList(borrowingRepository.findAll());
    }

    public BorrowingDetailDTO getBorrowingById(Long borrowingId) {
        return maptoDto(getBorrowingEntityByID(borrowingId));
    }

    @Transactional
    public Long createBorrowing(BorrowingRequestDTO borrowingRequestDTO) {
        BorrowingEntity enitity = mapToEntity(borrowingRequestDTO);
        return borrowingRepository.save(enitity).getId();
    }

    @Transactional
    public void updateBorrowing(Long borrowingId, BorrowingRequestDTO borrowingRequestDTO) {
        BorrowingEntity borrowing = getBorrowingEntityByID(borrowingId);

        BorrowingEntity updatedBorrowingEntity = mapToEntity(borrowingRequestDTO);
        BorrowingDetailDTO updatedDto = maptoDto(updatedBorrowingEntity);

        if((borrowingRequestDTO.getCustomerId()) != null){
            borrowing.setCustomerDetailDTO(updatedDto.getCustomerDetailDTO());
        }

        if((borrowingRequestDTO.getBookId()) != null){
            borrowing.setBookDetailDTO(updatedDto.getBookDetailDTO());
        }

        borrowing.setDate(new Date());
    }

    @Transactional
    public void deleteBorrowing(Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
    }

    private BorrowingEntity getBorrowingEntityByID(Long borrowingId) {
        Optional<BorrowingEntity>borrowing = borrowingRepository.findById(borrowingId);

        if(borrowing.isEmpty()) {
            throw new IllegalArgumentException("Borrowing not found. ID: " + borrowingId);
        }

        return borrowing.get();
    }


    private BorrowingEntity mapToEntity(BorrowingRequestDTO dto) {
        BorrowingEntity borrowing = new BorrowingEntity();

        if(!Objects.isNull(dto.getCustomerId()) ){
            Optional<CustomerEntity>customer = customerRepository.findById(dto.getCustomerId());

            if(customer.isPresent()){
                borrowing.setCustomer(customer.get());
            }
        }

        if(!Objects.isNull(dto.getBookId()) ){
            Optional<BookEntity>book = bookRepository.findById(dto.getBookId());

            if(book.isPresent()){
                borrowing.setBookEntity(book.get());
            }
        }

        return borrowing;
    }

    private List<BorrowingDetailDTO> mapToDtoList(Iterable<BorrowingEntity> borrowingEntities) {
        List<BorrowingDetailDTO> borrowings = new ArrayList<>();

        borrowingEntities.forEach(borrowingEntity -> {
            BorrowingDetailDTO dto = maptoDto(borrowingEntity);
            borrowings.add(dto);
        });

        return borrowings;
    }

    private BorrowingDetailDTO maptoDto(BorrowingEntity borrowingEntity){
        BorrowingDetailDTO dto = new BorrowingDetailDTO();
        dto.setId(borrowingEntity.getId());
        dto.setCustomerDetailDTO(borrowingEntity.getCustomerDetailDTO());
        dto.setBookDetailDTO(borrowingEntity.getBookDetailDTO());
        dto.setDate(new Date());

        return dto;
    }

}
