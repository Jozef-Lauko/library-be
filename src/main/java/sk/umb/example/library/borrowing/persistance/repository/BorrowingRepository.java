package sk.umb.example.library.borrowing.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.example.library.borrowing.persistance.entity.BorrowingEntity;

@Repository
public interface BorrowingRepository extends CrudRepository<BorrowingEntity, Long> {
    Iterable<BorrowingEntity> findByLastName(String lastName);
}
