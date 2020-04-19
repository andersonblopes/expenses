package com.lopes.expenses.controller;

import com.lopes.expenses.model.Expense;
import com.lopes.expenses.model.StatusExpense;
import com.lopes.expenses.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    private ExpenseService expenseService;

    private static final String EXPENSE_REGISTRATION = "expense-registration";
    private static final String EXPENSE_SEARCH = "expense-search";

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @RequestMapping("/new")
    public ModelAndView newExpense() {
        ModelAndView view = new ModelAndView(EXPENSE_REGISTRATION);
        view.addObject("expense", new Expense());
        return view;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Validated Expense expense, Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return EXPENSE_REGISTRATION;
        }

        try {
            expenseService.save(expense);
            redirectAttributes.addFlashAttribute("message", "Expense included successfully");
            return "redirect:/expense/new";
        } catch (IllegalArgumentException e) {
            errors.rejectValue("expenseDate", null, e.getMessage());
            return EXPENSE_REGISTRATION;
        }

    }

    @ModelAttribute("statusExpense")
    public List<StatusExpense> allStatusExpense() {
        return Arrays.asList(StatusExpense.values());
    }

    @RequestMapping
    public ModelAndView searchExpense() {
        List<Expense> allExpenses = expenseService.findAll();
        ModelAndView view = new ModelAndView(EXPENSE_SEARCH);
        view.addObject("expenses", allExpenses);
        return view;
    }


    @RequestMapping("{id}")
    public ModelAndView edit(@PathVariable("id") Expense expense) {
        ModelAndView view = new ModelAndView(EXPENSE_REGISTRATION);
        view.addObject(expense);
        return view;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        expenseService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Expense excluded successfully");
        return "redirect:/expense";
    }

}
