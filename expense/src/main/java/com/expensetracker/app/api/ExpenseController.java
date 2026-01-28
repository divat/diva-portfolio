package com.expensetracker.app.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.app.domain.Expense;
import com.expensetracker.app.persistence.mapper.ExpenseMapper;
import com.expensetracker.app.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    public ApiResponse<ExpenseResponse> createExpense(@Valid @RequestBody ExpenseRequest expenseRequest) {
        String userId = "USER_001";

        Expense expense = expenseService.createExpense(ExpenseMapper.toCommand(expenseRequest, userId));
        
        return ApiResponse.success(ExpenseMapper.toResponse(expense));

    }

    @GetMapping("/pageable")
    public ApiResponse<Page<Expense>> getExpenses(
        @RequestParam(defaultValue = "0", name = "page") int page,
        @RequestParam(defaultValue = "10", name = "size") int size,
        @RequestParam(defaultValue = "createdAt", name = "sortBy") String sortBy,
        @RequestParam(defaultValue = "DESC", name = "direction") Sort.Direction direction
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return ApiResponse.success(expenseService.getExpenses(pageable));
    }

    @GetMapping
    public ApiResponse<List<Expense>> getCursorPageBasedExpenses(
        @RequestParam(name = "limit", defaultValue = "10") int limit,
        @RequestParam(required = false, name = "cursor")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime cursor
    ) {
        return ApiResponse.success(expenseService.getCursorPageExpenses(LocalDate.now(), limit));
    }
}
