package com.lopes.expenses.service;

import com.lopes.expenses.model.Expense;
import com.lopes.expenses.model.StatusExpense;
import com.lopes.expenses.repository.ExpenseRepository;
import com.lopes.expenses.repository.filter.ExpenseFilter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Expense service.
 */
@Service
public class ExpenseService {

    /**
     * The Expense repository.
     */
    private final ExpenseRepository expenseRepository;

    /**
     * Instantiates a new Expense service.
     *
     * @param expenseRepository the expense repository
     */
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    /**
     * Save.
     *
     * @param expense the expense
     */
    public void save(Expense expense) {
        try {
            expenseRepository.save(expense);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Date invalid format");
        }

    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    /**
     * Find by filter list.
     *
     * @param filter the filter
     * @return the list
     */
    public List<Expense> findByFilter(ExpenseFilter filter) {
        return expenseRepository.findByDescriptionContaining(filter.getFilterDescription() != null ? filter.getFilterDescription() : "");
    }

    /**
     * Delete by id.
     *
     * @param id the id
     */
    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }

    /**
     * Receive expense string.
     *
     * @param id the id
     * @return the string
     */
    public String receiveExpense(Long id) {

        Expense expense = expenseRepository.findById(id).get();
        expense.setStatus(StatusExpense.DONE);
        expenseRepository.save(expense);

        return StatusExpense.DONE.getDescription();

    }
}
