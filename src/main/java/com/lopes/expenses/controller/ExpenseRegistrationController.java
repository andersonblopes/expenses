package com.lopes.expenses.controller;

import com.lopes.expenses.model.Expense;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/expense")
public class ExpenseRegistrationController {

    @RequestMapping("/new")
    public String newExpense() {
        return "expense-registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(Expense expense) {
        //TODO Save on database

        System.out.println(">>>>>>>> Expense: " + expense);

        return "expense-registration";
    }
}
