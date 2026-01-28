package com.expensetracker.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expensetracker.app.domain.Category;
import com.expensetracker.app.domain.CategoryMapper;
import com.expensetracker.app.persistence.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public List<Category> getCategories() {
        return categoryRepository.findByActiveTrue()
                    .stream()
                    .map(CategoryMapper::toModel)
                    .toList();
    }

    /*@Transactional
    public Category getCategoryByCode(String code) {
        return categoryRepository.findCategoryByCode(code);
    }*/
}
