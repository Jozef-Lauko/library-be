package sk.umb.example.library.book.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.example.library.book.persistance.enity.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {

}
