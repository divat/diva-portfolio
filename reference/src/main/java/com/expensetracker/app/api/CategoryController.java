package com.expensetracker.app.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.app.domain.Category;
import com.expensetracker.app.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getCategories());
    }

   /*  @GetMapping("/categories/{code}")
    public ResponseEntity<Category> getCategoryByCode(@PathVariable("code") String code) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getCategoryByCode(code));
    }*/

}
