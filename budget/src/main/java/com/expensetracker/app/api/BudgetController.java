package com.expensetracker.app.api;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.app.domain.Budget;
import com.expensetracker.app.service.BudgetService;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ApiResponse<Budget> create(
            @RequestBody BudgetRequest request) {
        return ApiResponse.success(budgetService.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<Budget> update(
            @PathVariable(name = "id") Long id,
            @RequestBody BudgetRequest request) {
        return ApiResponse.success(budgetService.update(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Budget> get(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(budgetService.get(id));
    }

    /*@GetMapping
    public ApiResponse<List<Budget>> getByMonth(
            @RequestParam YearMonth month) {
        return ApiResponse.success(budgetService.getByMonth(month));
    } */

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        budgetService.delete(id);
        return ApiResponse.success(null);
    }

    @GetMapping
    public ApiResponse<List<Budget>> getBudgets(@RequestParam(name = "userId") String userId,
                            @RequestParam(name = "period") String period ) {
        YearMonth yearMonth = YearMonth.parse(period);
        LocalDate periodDate = yearMonth.atDay(1);
        return ApiResponse.success(budgetService.getBudgetsByUserIdAndPeriod(userId, periodDate));
    }
}

