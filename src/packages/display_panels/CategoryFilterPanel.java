package assignment3.packages.src.packages.display_panels;

import assignment3.packages.src.packages.constructor.Expense;
import assignment3.packages.src.packages.expense_manager.ExpensesManager;
import assignment3.packages.src.packages.enums.Category;

import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Panel for filtering expenses by category
public class CategoryFilterPanel extends JPanel {
    private ExpensesManager expensesManager;
    private SavedExpensesPanel savedExpensesPanel;

    private JComboBox<Category> filterComboBox; // Combo box for selecting category
    private JButton filterButton; // Button for applying filter
    private JButton restoreButton; // Button for restoring filter
    private JButton sumButton; // Button for summing expenses
    private JLabel totalExpenseLabel; // Label to display total expense

    private Map<Category, List<Expense>> filteredExpensesMap = new HashMap<>();

    // Constructor
    public CategoryFilterPanel(ExpensesManager expensesManager, SavedExpensesPanel savedExpensesPanel) {
        this.expensesManager = expensesManager;
        this.savedExpensesPanel = savedExpensesPanel;

        setLayout(new FlowLayout(FlowLayout.LEFT)); // Set layout

        filterComboBox = new JComboBox<>(Category.values()); // Initialize filter combo box
        add(filterComboBox); // Add filter combo box to panel

        filterButton = new JButton("Filter"); // Initialize filter button
        add(filterButton); // Add filter button to panel

        restoreButton = new JButton("Restore"); // Initialize restore button
        add(restoreButton); // Add restore button to panel

        sumButton = new JButton("Sum"); // Initialize sum button
        add(sumButton); // Add sum button to panel

        totalExpenseLabel = new JLabel("Total Expense: 0"); // Initialize total expense label
        add(totalExpenseLabel); // Add total expense label to panel

        // Add action listener for the sum button
        sumButton.addActionListener(e -> sumExpenses());
    }

    // Method to get the filter button
    public JButton getFilterButton() {
        return filterButton;
    }

    // Method to get the restore button
    public JButton getRestoreButton() {
        return restoreButton;
    }

    // Method to get the sum button
    public JButton getSumButton() {
        return sumButton;
    }

    // Method to apply filter based on selected category
    public void applyFilter() {
        Object selectedCategory = (Category) filterComboBox.getSelectedItem(); // Get selected category
        System.out.println("Selected Category: " + selectedCategory); // Print selected category

        // Get all previously saved expenses from the savedExpensesPanel
        List<Expense> allExpenses = expensesManager.getAllExpenses();

        // Print all previously saved expenses to the terminal
        System.out.println("Previously Saved Expenses:");
        for (Expense expense : allExpenses) {
            System.out.println(expense);
        }

        // Filter previously saved expenses by the selected category
        List<Expense> filteredExpenses = allExpenses.stream()
                .filter(expense -> expense.category() == selectedCategory)
                .collect(Collectors.toList());

        // Print filtered expenses to the terminal
        System.out.println("Filtered Expenses for Category: " + selectedCategory);
        for (Expense expense : filteredExpenses) {
            System.out.println(expense);
        }

        // Update the table with filtered expenses
        System.out.println("Updating table with filtered expenses: " + filteredExpenses);
        savedExpensesPanel.updateTable(filteredExpenses);
    }



    // Method to restore filter
    public void restoreFilter() {
        List<Expense> allExpenses = expensesManager.getAllExpenses(); // Get all expenses
        savedExpensesPanel.updateTable(allExpenses); // Update table with all expenses
    }


    // Method to sum expenses
    public void sumExpenses() {
        // Get all expenses
        List<Expense> allExpenses = expensesManager.getAllExpenses();

        // Check if there are expenses with different currencies
        boolean hasDifferentCurrencies = allExpenses.stream()
                .map(Expense::currency)
                .distinct()
                .count() > 1;

        if (hasDifferentCurrencies) {
            // Display an error message
            JOptionPane.showMessageDialog(null, "Cannot sum expenses with different currencies", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calculate the sum
        double totalExpense = allExpenses.stream().mapToDouble(Expense::amount).sum();

        // Update the label to display total expense
        totalExpenseLabel.setText("Total Expense: " + totalExpense);
    }



}