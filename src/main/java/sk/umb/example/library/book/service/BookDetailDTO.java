package sk.umb.example.library.book.service;

import sk.umb.example.library.category.service.CategoryDetailDTO;

import java.util.Set;

public class BookDetailDTO {
    private Long id;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String isbn;
    private int count;
    private CategoryDetailDTO categoryDetailDTO;

    private Set<CategoryDetailDTO> categories;

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

    public Set<CategoryDetailDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDetailDTO> categories) {
        this.categories = categories;
    }

}
