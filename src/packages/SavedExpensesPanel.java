package assignment3.packages.src.packages;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Panel for displaying saved expenses
public class SavedExpensesPanel extends JPanel {
    private SavedExpenses savedExpenses; // SavedExpenses object to hold expenses data
    private JTable jsavedExpenseTable; // Table to display expenses
    private List<Expense> previouslySavedExpenses; // List to store previously saved expenses

    // Constructor
    public SavedExpensesPanel() {
        setLayout(new BorderLayout()); // Set layout

        savedExpenses = new SavedExpenses(new ArrayList<>()); // Initialize SavedExpenses object with empty list
        jsavedExpenseTable = new JTable(savedExpenses); // Initialize table with saved expenses data

        JScrollPane scrollPane = new JScrollPane(jsavedExpenseTable); // Create scroll pane for table
        add(scrollPane, BorderLayout.CENTER); // Add scroll pane to panel
    }

    // Method to update table with new list of expenses
    public void updateTable(List<Expense> expenses) {
        previouslySavedExpenses = savedExpenses.getExpenses(); // Store previously saved expenses
        savedExpenses.setExpenses(expenses); // Set new list of expenses in SavedExpenses object
    }

    // Method to get previously saved expenses
    public List<Expense> getpreviouslySavedExpenses() {
        return previouslySavedExpenses; // Return previously saved expenses
    }

    // Method to get index of selected expense in the table
    public int getSavedSelectedExpenseIndex() {
        return jsavedExpenseTable.getSelectedRow(); // Returns -1 if no row is selected
    }
}
