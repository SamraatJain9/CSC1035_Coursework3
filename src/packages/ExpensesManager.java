package assignment3.packages.src.packages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Manager class for handling expenses
public class ExpensesManager {
    private List<Expense> expenses; // List to store expenses

    // Constructor
    public ExpensesManager() {
        expenses = new ArrayList<>(); // Initialize expenses list
    }

    // Method to add an expense to the existing list of expenses
    public void addExpense(Expense expense) {
        expenses.add(expense); // Add expense to list
    }

    // Method to replace an expense at a specific index
    public void replaceExpense(int index, Expense newExpense) {
        if (index >= 0 && index < expenses.size()) {
            expenses.set(index, newExpense); // Replace expense at index
        }
    }

    // Method to remove an expense at a specific index
    public void removeExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index); // Remove expense at index
        }
    }

    // Method to clear all expenses
    public void clearExpenses() {
        expenses.clear(); // Clear all expenses
    }

    // Method to return the entire list of expenses added so far
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses); // Return a copy of the expenses list
    }

    // Method to get expenses based on category for CategoryFilterPanel
    public List<Expense> getExpensesByCategory(Category category) {
        return expenses.stream()
                .filter(expense -> expense.category().equals(category))
                .collect(Collectors.toList()); // Filter expenses by category and return as list
    }
}
