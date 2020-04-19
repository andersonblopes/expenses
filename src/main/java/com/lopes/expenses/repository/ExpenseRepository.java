package com.lopes.expenses.repository;

import com.lopes.expenses.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Expense repository.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
