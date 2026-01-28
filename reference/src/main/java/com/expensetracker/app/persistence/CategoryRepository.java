package com.expensetracker.app.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expensetracker.app.domain.CategoryEntity;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    /*@Query("SELECT c FROM Category c WHERE c.code = :code")
    public CategoryEntity findCategoryByCode(@Param("code") String code);*/

    List<CategoryEntity> findByActiveTrue();

}
