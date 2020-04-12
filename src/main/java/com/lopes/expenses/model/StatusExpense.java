package com.lopes.expenses.model;

public enum StatusExpense {

    //TODO Implements internationalization
    PENDING("Pending"),
    DONE("Done");

    private String description;

    StatusExpense(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
