package com.lopes.expenses.repository;

import com.lopes.expenses.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Expense repository.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    /**
     * Find by description containing list.
     *
     * @param description the description
     * @return the list
     */
    public List<Expense> findByDescriptionContaining(String description);

}
