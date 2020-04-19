package com.lopes.expenses.service;

import com.lopes.expenses.model.Expense;
import com.lopes.expenses.repository.ExpenseRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void save(Expense expense) {
        try {
            expenseRepository.save(expense);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Date invalid format");
        }

    }

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }
}
