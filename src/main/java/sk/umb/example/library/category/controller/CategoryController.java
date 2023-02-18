package sk.umb.example.library.category.controller;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLData;
import java.util.Collections;

@RestController
public class CategoryController {
    @GetMapping("/api/category")
    public void searchCategory(@RequestParam(required = false) String genre){
        System.out.println("Search category called.");
    }

    @GetMapping("api/category/{categoryId}")
    public void getCategory(@PathVariable Long categoryId){
        System.out.println("Get category called.");
    }

    @PostMapping("api/category/{categoryId}")
    public void createCategory(){
        System.out.println("Create category");
    }

    @PutMapping("api/category/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId){
        System.out.println("Update category" + categoryId);
    }

    @DeleteMapping("api/category/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        System.out.println("Delete category" + categoryId);
    }


}