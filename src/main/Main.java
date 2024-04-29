package assignment3.packages.src.main;

// Import necessary packages
import assignment3.packages.src.packages.display_panels.CategoryFilterPanel;
import assignment3.packages.src.packages.display_panels.NewExpensesPanel;
import assignment3.packages.src.packages.display_panels.SavedExpensesPanel;
import assignment3.packages.src.packages.edit_dialogbox.SavedExpensesEditDialog;
import assignment3.packages.src.packages.expense_manager.ExpensesManager;
import assignment3.packages.src.packages.expense_record.Expense;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

// Main class
public class Main {

    // Main method
    public static void main(String[] args) {
        createAndShowGUI(); // Create and show the GUI
    }

    // Method to create and show the GUI
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Expense Tracker"); // Create a JFrame with title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        frame.setLayout(new BorderLayout()); // Set layout to BorderLayout

        // Create the object of expenseManager
        ExpensesManager expensesManager = new ExpensesManager();

        // Create instances of panels
        NewExpensesPanel newExpensesPanel = new NewExpensesPanel();
        SavedExpensesPanel savedExpensesPanel = new SavedExpensesPanel();
        CategoryFilterPanel categoryFilterPanel = new CategoryFilterPanel(expensesManager, savedExpensesPanel);

        // Add panels to the frame and specify their relative position in the frame
        frame.add(newExpensesPanel, BorderLayout.NORTH);
        frame.add(savedExpensesPanel, BorderLayout.CENTER);
        frame.add(categoryFilterPanel, BorderLayout.SOUTH);

        frame.pack(); // Pack components within the frame
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true); // Make the frame visible

        // Listener for clicking save button
        newExpensesPanel.getSaveButton().addActionListener(e -> {
            double amount = newExpensesPanel.getAmount(); // Get entered amount
            // Check if amount is valid
            if (amount <= 0) {
                JOptionPane.showMessageDialog(frame, "Amount must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop further execution
            }

            String currency = String.valueOf(newExpensesPanel.getExpenseCurrency()); // Get selected currency
            String category = String.valueOf(newExpensesPanel.getExpenseCategory()); // Get selected category
            LocalDate date = newExpensesPanel.getDate(); // Get selected date

            // Create new expense
            Expense newExpense = new Expense(amount, currency, category, date);
            expensesManager.addExpense(newExpense); // Add expense to manager
            savedExpensesPanel.updateTable(expensesManager.getAllExpenses()); // Update saved expenses panel
        });

        // Edit Button ActionListener
        newExpensesPanel.getEditButton().addActionListener(e -> {
            int selectedRow = savedExpensesPanel.getSavedSelectedExpenseIndex();
            if (selectedRow != -1) {
                Expense selectedExpense = expensesManager.getAllExpenses().get(selectedRow);
                SavedExpensesEditDialog editDialog = new SavedExpensesEditDialog(frame);
                boolean saved = editDialog.showDialog(selectedExpense);
                if (saved) {
                    Expense editedExpense = editDialog.getEditedExpense();
                    expensesManager.replaceExpense(selectedRow, editedExpense);
                    savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
                }
            }
        });

        // Delete button ActionListener
        newExpensesPanel.getDeleteButton().addActionListener(e -> {
            int selectedRow = savedExpensesPanel.getSavedSelectedExpenseIndex();
            if (selectedRow != -1) {
                expensesManager.removeExpense(selectedRow); // Remove expense from manager
                savedExpensesPanel.updateTable(expensesManager.getAllExpenses()); // Update saved expenses panel
            }
        });

        // Clear Button ActionListener
        newExpensesPanel.getClearButton().addActionListener(e -> {
            expensesManager.clearExpenses(); // Clear all expenses
            savedExpensesPanel.updateTable(expensesManager.getAllExpenses()); // Update saved expenses panel
        });

        // Filter Button ActionListener
        categoryFilterPanel.getFilterButton().addActionListener(e -> categoryFilterPanel.applyFilter());

        // Restore Button ActionListener
        categoryFilterPanel.getRestoreButton().addActionListener(e -> categoryFilterPanel.restoreFilter());

        // Sum button ActionListener
        categoryFilterPanel.getSumButton().addActionListener(e -> categoryFilterPanel.sumExpenses());
    }
}