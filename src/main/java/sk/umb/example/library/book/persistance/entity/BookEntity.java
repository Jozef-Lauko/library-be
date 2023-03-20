package sk.umb.example.library.book.persistance.entity;

import jakarta.persistence.*;
import sk.umb.example.library.category.persistance.entity.CategoryEntity;

import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "category_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> categories;

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

<<<<<<< HEAD:src/main/java/sk/umb/example/library/book/persistance/enity/BookEntity.java
    public String getCategory() {
        return category;
    }

    public void setCategory(CategoryDetailDTO categoryCategoryDTO) {
        this.categoryDetailDTO = categoryCategoryDTO;
=======
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
>>>>>>> c8ed65dedbb308f70defdf7302e4684f7700d278:src/main/java/sk/umb/example/library/book/persistance/entity/BookEntity.java
    }
}
