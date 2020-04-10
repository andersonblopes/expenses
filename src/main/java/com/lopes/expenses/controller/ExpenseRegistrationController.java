package com.lopes.expenses.controller;

import com.lopes.expenses.model.Expense;
import com.lopes.expenses.repository.ExpenseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/expense")
public class ExpenseRegistrationController {

    private final ExpenseRepository expenseRepository;

    public ExpenseRegistrationController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @RequestMapping("/new")
    public String newExpense() {
        return "expense-registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(Expense expense) {
        expenseRepository.save(expense);
        return "expense-registration";
    }
}
