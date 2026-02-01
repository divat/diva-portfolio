package com.expensetracker.app.persistence;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBudgetRepository extends JpaRepository<BudgetEntity, Long>  {

    Optional<BudgetEntity> findByIdAndActiveTrue(Long id);

    List<BudgetEntity> findByMonthAndActiveTrue(YearMonth month);

    Optional<BudgetEntity> findByCategoryIdAndMonthAndActiveTrue(
            Long categoryId,
            LocalDate month);

    List<BudgetEntity> findByUserIdAndMonthAndActiveTrue(String userId, LocalDate month);
    
}
