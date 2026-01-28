package com.expensetracker.app.domain;

public class CategoryMapper {

    public static Category toModel(CategoryEntity categoryEntity) {
        return new Category(categoryEntity.getCode(), categoryEntity.getName());
    }
}
