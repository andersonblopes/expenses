package com.lopes.expenses.controller;

import com.lopes.expenses.model.Expense;
import com.lopes.expenses.model.StatusExpense;
import com.lopes.expenses.repository.filter.ExpenseFilter;
import com.lopes.expenses.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * The type Expense controller.
 */
@Controller
@RequestMapping("/expense")
public class ExpenseController {

    /**
     * The Expense service.
     */
    private ExpenseService expenseService;

    /**
     * The constant EXPENSE_REGISTRATION.
     */
    private static final String EXPENSE_REGISTRATION = "expense-registration";
    /**
     * The constant EXPENSE_SEARCH.
     */
    private static final String EXPENSE_SEARCH = "expense-search";

    /**
     * Instantiates a new Expense controller.
     *
     * @param expenseService the expense service
     */
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /**
     * New expense model and view.
     *
     * @return the model and view
     */
    @RequestMapping("/new")
    public ModelAndView newExpense() {
        ModelAndView view = new ModelAndView(EXPENSE_REGISTRATION);
        view.addObject("expense", new Expense());
        return view;
    }

    /**
     * Save string.
     *
     * @param expense            the expense
     * @param errors             the errors
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
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

    /**
     * All status expense list.
     *
     * @return the list
     */
    @ModelAttribute("statusExpense")
    public List<StatusExpense> allStatusExpense() {
        return Arrays.asList(StatusExpense.values());
    }


    /**
     * Search expense model and view.
     *
     * @param filter the filter
     * @return the model and view
     */
    @RequestMapping
    public ModelAndView searchExpense(@ModelAttribute("filter") ExpenseFilter filter) {
        List<Expense> expensesFound = expenseService.findByFilter(filter);
        ModelAndView view = new ModelAndView(EXPENSE_SEARCH);
        view.addObject("expenses", expensesFound);
        return view;
    }

    /**
     * Edit model and view.
     *
     * @param expense the expense
     * @return the model and view
     */
    @RequestMapping("{id}")
    public ModelAndView edit(@PathVariable("id") Expense expense) {
        ModelAndView view = new ModelAndView(EXPENSE_REGISTRATION);
        view.addObject(expense);
        return view;
    }

    /**
     * Delete string.
     *
     * @param id                 the id
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        expenseService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Expense excluded successfully");
        return "redirect:/expense";
    }


    /**
     * Receive expense string.
     *
     * @param id the id
     * @return the string
     */
    @RequestMapping(value = "/{id}/receive", method = RequestMethod.PUT)
    @ResponseBody
    public String receiveExpense(@PathVariable Long id) {
        return expenseService.receiveExpense(id);
    }
}
