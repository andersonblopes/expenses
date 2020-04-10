package com.lopes.expenses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpenseRegistrationController {

    @RequestMapping("expense/new")
    public String newExpense() {
        return "expense-registration";
    }
}
