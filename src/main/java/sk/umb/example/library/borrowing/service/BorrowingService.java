package sk.umb.example.library.borrowing.service;

import sk.umb.example.library.book.service.BookDetailDTO;
import sk.umb.example.library.book.service.BookService;
import sk.umb.example.library.customer.service.CustomerDetailDTO;
import sk.umb.example.library.customer.service.CustomerRequestDTO;
import sk.umb.example.library.customer.service.CustomerService;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class BorrowingService {
    private final Map<Long, BorrowingDetailDTO> borrowingDatabase = new HashMap();
    private final AtomicLong lastIndex = new AtomicLong(0);


    public List<BorrowingDetailDTO> getAllBorrowings() {
        return new ArrayList<>(borrowingDatabase.values());
    }

    public BorrowingDetailDTO getBorrowingById(Long borrowingId) {
        validateBorrowingExists(borrowingId);

        return borrowingDatabase.get(borrowingId);
    }

    public Long createBorrowing(BorrowingRequestDTO borrowingRequestDTO) {
        BorrowingDetailDTO borrowingDetailDTO = mapToBorrowingDetailDTO(lastIndex.getAndIncrement(),
                borrowingRequestDTO);

        borrowingDatabase.put(borrowingDetailDTO.getId(), borrowingDetailDTO);

        return borrowingDetailDTO.getId();
    }

    private BorrowingDetailDTO mapToBorrowingDetailDTO(Long index, BorrowingRequestDTO borrowingRequestDTO) {
        BorrowingDetailDTO dto = new BorrowingDetailDTO();

        CustomerDetailDTO cdDTO = new CustomerDetailDTO();
        cdDTO.setId(borrowingRequestDTO.getCustomerId());
        BookDetailDTO bDTO = new BookDetailDTO();
        bDTO.setId(borrowingRequestDTO.getBookId());

        dto.setId(index);
        dto.setCustomerDetailDTO(cdDTO);
        dto.setBookDetailDTO(bDTO);
        dto.setDate(new Date());

        return dto;
    }

    public void updateBorrowing(Long borrowingId, BorrowingRequestDTO borrowingRequestDTO) {
        validateBorrowingExists(borrowingId);

        BorrowingDetailDTO dto = borrowingDatabase.get(borrowingId);

        CustomerDetailDTO cdDTO = new CustomerDetailDTO();
        cdDTO.setId(borrowingRequestDTO.getCustomerId());
        BookDetailDTO bDTO = new BookDetailDTO();
        bDTO.setId(borrowingRequestDTO.getBookId());

        dto.setCustomerDetailDTO(cdDTO);
        dto.setBookDetailDTO(bDTO);
        dto.setDate(new Date());
    }

    public void deleteBorrowing(Long borrowingId) {
        validateBorrowingExists(borrowingId);

        borrowingDatabase.remove(borrowingId);
    }

    private void validateBorrowingExists(Long borrowingId) {
        if(! borrowingDatabase.containsKey(borrowingId)) {
            throw new IllegalArgumentException("BorrowingID: "+borrowingId+" does not exist.");
        }
    }
}
