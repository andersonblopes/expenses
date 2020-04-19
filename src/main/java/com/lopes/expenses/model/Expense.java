package com.lopes.expenses.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * The type Expense.
 */
@Entity
public class Expense {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Description.
     */
    @NotEmpty(message = "Description cannot be empty ")
    @Size(max = 60, message = "Description must be no longer than 60 characters.")
    @Column(name = "expense_description")
    private String description;

    /**
     * The Expense date.
     */
    @NotNull(message = "Expense date is required.")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date expenseDate;

    /**
     * The Value.
     */
    @NotNull(message = "Expense value cannot be empty")
    @DecimalMin(value = "0.01", message = "Value cannot be less than 0.01")
    @DecimalMax(value = "99999999999.99", message = "Value cannot be greater than 99999999999,99")
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal value;

    /**
     * The Status.
     */
    @Enumerated(EnumType.STRING)
    private StatusExpense status;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets expense date.
     *
     * @return the expense date
     */
    public Date getExpenseDate() {
        return expenseDate;
    }

    /**
     * Sets expense date.
     *
     * @param expenseDate the expense date
     */
    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public StatusExpense getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(StatusExpense status) {
        this.status = status;
    }

    /**
     * Is pending boolean.
     *
     * @return the boolean
     */
    public boolean isPending() {
        return status.equals(StatusExpense.PENDING);
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Expense expense = (Expense) o;
        return id.equals(expense.id);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", expenseDate=" + expenseDate +
                ", value=" + value +
                ", status=" + status +
                '}';
    }
}
