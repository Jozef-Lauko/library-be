package sk.umb.example.library.book.persistance.enity;

import jakarta.persistence.*;
import sk.umb.example.library.category.service.CategoryDetailDTO;

@Entity(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String isbn;
    private int count;
    private CategoryDetailDTO categoryDetailDTO;

    @ManyToMany
    @JoinTable(name="category_book",
            joinColumns=@JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="category_id"))
    private Set<Category> categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CategoryDetailDTO getCategory() {
        return categoryDetailDTO;
    }

    public void setCategory(CategoryDetailDTO categoryDetailDTO) {
        this.categoryDetailDTO = categoryDetailDTO;
    }
}
