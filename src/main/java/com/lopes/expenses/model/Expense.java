package com.lopes.expenses.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Description cannot be empty ")
    @Size(max = 60, message = "Description must be no longer than 60 characters.")
    @Column(name = "expense_description")
    private String description;

    @NotNull(message = "Expense date is required.")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date expenseDate;

    @NotNull(message = "Expense value cannot be empty")
    @DecimalMin(value = "0.01", message = "Value cannot be less than 0.01")
    @DecimalMax(value = "99999999999.99", message = "Value cannot be greater than 99999999999,99")
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private StatusExpense status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public StatusExpense getStatus() {
        return status;
    }

    public void setStatus(StatusExpense status) {
        this.status = status;
    }

    public boolean isPending() {
        return status.equals(StatusExpense.PENDING);
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

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
