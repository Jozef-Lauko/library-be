package sk.umb.example.library.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.category.service.CategoryDetailDTO;
import sk.umb.example.library.category.service.CategoryRequestDTO;
import sk.umb.example.library.category.service.CategoryService;


import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @PostMapping("/api/categories")
    public CategoryDetailDTO createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        System.out.println("Create category called:");

        return categoryService.createCategory(categoryRequestDTO);
    }

    @GetMapping("/api/categories/{categoryId}")
    public CategoryDetailDTO getCategory(@PathVariable Long categoryId) {
        System.out.println("Get category called." + categoryId);

        return categoryService.getCategory(categoryId);
    }

    @GetMapping("/api/categories")
    public List<CategoryDetailDTO> getAllCategories() {
        System.out.println("Get all categories:");
        return categoryService.getAllCategories();
    }

    @PutMapping("/api/categories/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId,
                                    @RequestBody CategoryRequestDTO categoryRequestDTO) {
        System.out.println("Update category called: ID = " + categoryId);
        categoryService.updateCategory(categoryId, categoryRequestDTO);
    }

    @DeleteMapping("/api/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        System.out.println("Delete category called: ID = " + categoryId);

        categoryService.deleteCategory(categoryId);
    }

}