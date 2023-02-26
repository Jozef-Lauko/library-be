package sk.umb.example.library.book.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {

    private final Map<Long, BookDetailDTO> bookDatabase = new HashMap<>();
    private final AtomicLong lastIndex = new AtomicLong(0);

    public BookDetailDTO createBook(BookRequestDTO bookRequestDTO) {
        BookDetailDTO bookDetailDTO = new BookDetailDTO();
        bookDetailDTO.setId(lastIndex.getAndIncrement());
        bookDetailDTO.setAuthorFirstName(Strings.isBlank(bookRequestDTO.getAuthorFirstName()) ? null : bookRequestDTO.getAuthorFirstName());
        bookDetailDTO.setAuthorLastName(Strings.isBlank(bookRequestDTO.getAuthorLastName()) ? null : bookRequestDTO.getAuthorLastName());
        bookDetailDTO.setTitle(Strings.isBlank(bookRequestDTO.getTitle()) ? null : bookRequestDTO.getTitle());
        bookDetailDTO.setIsbn(Strings.isBlank(bookRequestDTO.getIsbn()) ? null : bookRequestDTO.getIsbn());
        bookDetailDTO.setCount(bookRequestDTO.getCount());
        bookDetailDTO.setCategoryIds(bookRequestDTO.getCategoryIds());
        bookDatabase.put(bookDetailDTO.getId(), bookDetailDTO);
        return bookDetailDTO;
    }

    public BookDetailDTO getBook(Long bookId) {
        return bookDatabase.get(bookId);
    }

    public List<BookDetailDTO> getAllBooks() {
        return new ArrayList<>(bookDatabase.values());
    }

    public BookDetailDTO updateBook(Long bookId, BookRequestDTO bookRequestDTO) {
        BookDetailDTO bookDetailDTO = bookDatabase.get(bookId);
        if (bookDetailDTO != null) {
            bookDetailDTO.setAuthorFirstName(Strings.isBlank(bookRequestDTO.getAuthorFirstName()) ? null : bookRequestDTO.getAuthorFirstName());
            bookDetailDTO.setAuthorLastName(Strings.isBlank(bookRequestDTO.getAuthorLastName()) ? null : bookRequestDTO.getAuthorLastName());
            bookDetailDTO.setTitle(Strings.isBlank(bookRequestDTO.getTitle()) ? null : bookRequestDTO.getTitle());
            bookDetailDTO.setIsbn(Strings.isBlank(bookRequestDTO.getIsbn()) ? null : bookRequestDTO.getIsbn());
            bookDetailDTO.setCount(bookRequestDTO.getCount());
            bookDetailDTO.setCategoryIds(bookRequestDTO.getCategoryIds());
            return bookDetailDTO;
        }
        return null;
    }

    public boolean deleteBook(Long bookId) {
        return bookDatabase.remove(bookId) != null;
    }
}
