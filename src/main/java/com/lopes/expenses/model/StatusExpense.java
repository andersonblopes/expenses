package com.lopes.expenses.model;

/**
 * The enum Status expense.
 */
public enum StatusExpense {

    /**
     * The Pending.
     */
//TODO Implements internationalization
    PENDING("Pending"),
    /**
     * Done status expense.
     */
    DONE("Done");

    /**
     * The Description.
     */
    private String description;

    /**
     * Instantiates a new Status expense.
     *
     * @param description the description
     */
    StatusExpense(String description) {
        this.description = description;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }


}
