package sk.umb.example.library.book.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import sk.umb.example.library.book.persistance.enity.BookEntity;
import sk.umb.example.library.category.persistance.entity.CategoryEntity;
import sk.umb.example.library.category.service.CategoryDetailDTO;

import java.util.*;
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
    private List<BookDetailDTO> mapToDtoList(Iterable<BookEntity> bookEntities){
        List<BookDetailDTO> books = new ArrayList<>();

        bookEntities.forEach(bookEntity -> {
            BookDetailDTO dto = mapToDto(bookEntity);
            books.add(dto);
        });

        return books;
    }

    private BookDetailDTO mapToDto(BookEntity bookEntity) {
        BookDetailDTO dto = new BookDetailDTO();

        dto.setId(bookEntity.getId());
        dto.setIsbn(bookEntity.getIsbn());
        dto.setCount(bookEntity.getCount());
        dto.setTitle(bookEntity.getTitle());
        dto.setAuthorFirstName(bookEntity.getAuthorFirstName());
        dto.setAuthorLastName(bookEntity.getAuthorLastName());
        if (bookEntity.getCategory() != null) {
            Set<CategoryDetailDTO> categoryDTOs = new HashSet<>();
            for (CategoryEntity categoryEntity : bookEntity.getCategories()) {
                CategoryDetailDTO categoryDTO = new CategoryDetailDTO();
                categoryDTO.setId(categoryEntity.getId());
                categoryDTO.setCategory(categoryEntity.getCategoryName());
                categoryDTOs.add(categoryDTO);
            }
            dto.setCategories(categoryDTOs);
        }

        return dto;
    }

}
