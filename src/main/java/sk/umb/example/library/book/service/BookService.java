package sk.umb.example.library.book.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sk.umb.example.library.book.persistance.entity.BookEntity;
import sk.umb.example.library.book.persistance.repository.BookRepository;
import sk.umb.example.library.category.persistance.entity.CategoryEntity;
import sk.umb.example.library.category.service.CategoryDetailDTO;

import java.util.*;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final List<BookDetailDTO> books = new ArrayList<>();
    public List<BookDetailDTO> getAllBooks(){
        return mapToDtoList(bookRepository.findAll());
    }
    public BookDetailDTO getBookById(Long bookId){
        return mapToDto(getBookEntityByID(bookId));
    }


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDetailDTO getBook(Long bookId) {
        return mapToDto(getBookEntityByID(bookId));
    }

    @Transactional
    public Long createBook(BookRequestDTO bookRequestDTO){
        BookEntity entity = mapToEntity(bookRequestDTO);
        return bookRepository.save(entity).getId();
    }

    @Transactional
    public void updateBook(Long bookId, BookRequestDTO bookRequestDTO){
        BookEntity book = getBookEntityByID(bookId);

        BookEntity updateBookEntity = mapToEntity(bookRequestDTO);
        BookDetailDTO updateDto = mapToDto(updateBookEntity);
    }

    @Transactional
    public void deleteBook(Long bookId){
        bookRepository.deleteById(bookId);
    }

    private BookEntity getBookEntityByID(Long bookId) {
        Optional<BookEntity> book = bookRepository.findById(bookId);

        if(book.isEmpty()) {
            throw new IllegalArgumentException("Book not found. ID: " + bookId);
        }

        return book.get();
    }

    private BookEntity mapToEntity(BookRequestDTO dto){
        BookEntity book = new BookEntity();

        book.setIsbn(dto.getIsbn());
        book.setCount(dto.getCount());
        book.setTitle(dto.getTitle());
        book.setAuthorFirstName(dto.getAuthorFirstName());
        book.setAuthorLastName(dto.getAuthorLastName());

        return book;
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
        if (bookEntity.getCategories() != null) {
            Set<CategoryDetailDTO> categoryDTOs = new HashSet<>();
            for (CategoryEntity categoryEntity : bookEntity.getCategories()) {
                CategoryDetailDTO categoryDTO = new CategoryDetailDTO();
                categoryDTO.setId(categoryEntity.getId());
                categoryDTO.setCategory(categoryEntity.getCategory());
                categoryDTOs.add(categoryDTO);
            }
            dto.setCategories(categoryDTOs);
        }

        return dto;
    }

}
