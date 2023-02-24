package sk.umb.example.library.borrowing.service;

import sk.umb.example.library.customer.service.CustomerDetailDTO;
import sk.umb.example.library.customer.service.CustomerRequestDTO;

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

    private static BorrowingDetailDTO mapToBorrowingDetailDTO(Long index, BorrowingRequestDTO borrowingRequestDTO) {
        BorrowingDetailDTO dto = new BorrowingDetailDTO();

        dto.setId(index);
        dto.setCustomerDetailDTO(borrowingRequestDTO.getCustomerId());
        dto.setBookDetailDTO(borrowingRequestDTO.getBookId());
        dto.setDate(new Date());

        return dto;
    }

    private void validateBorrowingExists(Long borrowingId) {
        if(! borrowingDatabase.containsKey(borrowingId)) {
            throw new IllegalArgumentException("BorrowingID: "+borrowingId+" does not exist.");
        }
    }
}
