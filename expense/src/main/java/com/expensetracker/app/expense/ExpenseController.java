
package com.expensetracker.app.expense;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses/api/")
public class ExpenseController {

    @GetMapping("ping")
    public String ping() {
        return "Expense module working";
    }
}
