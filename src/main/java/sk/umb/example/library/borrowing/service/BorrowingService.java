package sk.umb.example.library.borrowing.service;

import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class BorrowingService {
    private final Map<Long, BorrowingDetailDTO> borrowingDatabase = new HashMap();
    private final AtomicLong lastIndex = new AtomicLong(0);


    public List<BorrowingDetailDTO> getAllBorrowings() {
        return new ArrayList<>(borrowingDatabase.values());
    }

    public List<BorrowingDetailDTO> searchBorrowingByLastName(String lastName) {
        return borrowingDatabase.values().stream()
                .filter(dto -> lastName.equals(dto.getCustomerDetailDTO().getLastName()))
                .toList();
    }

    public BorrowingDetailDTO getBorrowingById(Long borrowingId) {
        validateBorrowingExists(borrowingId);

        return borrowingDatabase.get(borrowingId);
    }

    public Long createBorrowing(BorrowingRequestDTO borrowingRequestDTO) {
        BorrowingDetailDTO borrowingDetailDTO = mapToBorrowingDetialDTO(lastIndex.getAndIncrement(), borrowingRequestDTO);

        borrowingDatabase.put(borrowingDetailDTO.getId(), borrowingDetailDTO);

        return borrowingDetailDTO.getId();
    }

    public void updateBorrowing(Long borrowingId, BorrowingRequestDTO borrowingRequestDTO) {
        validateBorrowingExists(borrowingId);

        BorrowingDetailDTO borrowingDetailDTO = borrowingDatabase.get(borrowingId);

        if (borrowingRequestDTO.getCustomerId() != null) {
            borrowingDetailDTO.setId(borrowingRequestDTO.getCustomerId().getId());
        }
    }

    private BorrowingDetailDTO mapToBorrowingDetialDTO(long index, BorrowingRequestDTO borrowingRequestDTO) {
        BorrowingDetailDTO dto = new BorrowingDetailDTO();

        dto.setId(index);
        dto.setCustomerDetailDTO(borrowingRequestDTO.getCustomerId());
//        dto.setBookDetailDTO(borrowingRequestDTO.geBookId());

        return dto;
    }

    private void validateBorrowingExists(Long borrowingId) {
        if (!borrowingDatabase.containsKey(borrowingId)) {
            throw new IllegalArgumentException("BorrowingId: "+borrowingId+ " does not exists!");
        }
    }

}
