package com.lopes.expenses.controller;

import com.lopes.expenses.model.Expense;
import com.lopes.expenses.model.StatusExpense;
import com.lopes.expenses.repository.ExpenseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/expense")
public class ExpenseRegistrationController {

    private final ExpenseRepository expenseRepository;

    public ExpenseRegistrationController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @RequestMapping("/new")
    public ModelAndView newExpense() {
        ModelAndView view = new ModelAndView("expense-registration");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(Expense expense) {
        expenseRepository.save(expense);
        ModelAndView view = new ModelAndView("expense-registration");
        view.addObject("message", "Expense included successfully");
        return view;
    }

    @ModelAttribute("statusExpense")
    public List<StatusExpense> allStatusExpense() {
        return Arrays.asList(StatusExpense.values());
    }

    @RequestMapping
    public String searchExpense() {
        return "expense-search";
    }
}
